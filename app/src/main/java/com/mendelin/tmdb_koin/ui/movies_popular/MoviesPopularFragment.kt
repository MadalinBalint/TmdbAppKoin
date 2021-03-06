package com.mendelin.tmdb_koin.ui.movies_popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.mendelin.tmdb_koin.R
import com.mendelin.tmdb_koin.databinding.FragmentMoviesPopularBinding
import com.mendelin.tmdb_koin.data.repository.local.PreferencesRepository
import com.mendelin.tmdb_koin.ui.custom_view.MarginItemVerticalDecoration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MoviesPopularFragment : Fragment() {
    private val viewModel by sharedViewModel<MoviesPopularViewModel>()
    private var binding: FragmentMoviesPopularBinding? = null
    private lateinit var moviesPopularAdapter: MoviesPopularAdapter
    private val preferences: PreferencesRepository by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoviesPopularBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        observeViewModel()
    }

    override fun onResume() {
        super.onResume()

        /* Restore last position */
        CoroutineScope(Dispatchers.Main).launch {
            binding?.recyclerPopularMovies?.apply {
                if (viewModel.firstLoad) delay(200)
                scrollToPosition(preferences.getPostion(PreferencesRepository.KEY_MOVIES_POPULAR, 0).first())
            }
        }
    }

    override fun onPause() {
        val manager = binding?.recyclerPopularMovies?.layoutManager as LinearLayoutManager
        val pos = if (manager.findFirstCompletelyVisibleItemPosition() > -1) manager.findFirstCompletelyVisibleItemPosition() else manager.findFirstVisibleItemPosition()

        /* Save last position */
        CoroutineScope(Dispatchers.IO).launch {
            preferences.updatePosition(PreferencesRepository.KEY_MOVIES_POPULAR, pos)
        }

        viewModel.firstLoad = false

        super.onPause()
    }

    private fun setupUI() {
        moviesPopularAdapter = MoviesPopularAdapter()

        binding?.recyclerPopularMovies?.apply {
            adapter = moviesPopularAdapter
            layoutManager = LinearLayoutManager(requireActivity())
            isNestedScrollingEnabled = true

            addItemDecoration(
                MarginItemVerticalDecoration(
                    resources.getDimension(R.dimen.recyclerview_margin_horizontal).toInt(),
                    resources.getDimension(R.dimen.recyclerview_margin_vertical).toInt()
                )
            )
        }

        binding?.swipePopularMovies?.setOnRefreshListener {
            moviesPopularAdapter.refresh()
            binding?.swipePopularMovies?.isRefreshing = false
        }
    }

    private fun observeViewModel() {
        viewModel.getErrorFilter().observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                Snackbar.make(binding?.frameLayout!!, it, Snackbar.LENGTH_LONG)
                    .setAction("Retry") {
                        moviesPopularAdapter.refresh()
                    }
                    .show()
                viewModel.onErrorHandled()
            }
        }

        viewModel.getLoadingObservable().observe(viewLifecycleOwner) {
            binding?.progressPopularMovies?.visibility = if (it == true) View.VISIBLE else View.INVISIBLE
        }

        lifecycleScope.launch {
            moviesPopularAdapter.loadStateFlow.collectLatest { state ->
                viewModel.isLoading.value = state.refresh is LoadState.Loading

                val errorState = state.refresh as? LoadState.Error
                    ?: state.source.append as? LoadState.Error
                    ?: state.source.prepend as? LoadState.Error
                    ?: state.append as? LoadState.Error
                    ?: state.prepend as? LoadState.Error

                errorState?.let {
                    viewModel.error.value = it.error.message
                }
            }
        }

        lifecycleScope.launch {
            viewModel.popularMovies.collectLatest {
                moviesPopularAdapter.submitData(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}