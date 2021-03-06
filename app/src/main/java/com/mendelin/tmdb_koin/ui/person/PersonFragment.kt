package com.mendelin.tmdb_koin.ui.person

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.mendelin.tmdb_koin.R
import com.mendelin.tmdb_koin.databinding.FragmentPersonBinding
import com.mendelin.tmdb_koin.ui.custom_view.MarginItemVerticalDecoration
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber

class PersonFragment : Fragment() {
    private val viewModel by sharedViewModel<PersonViewModel>()
    private var binding: FragmentPersonBinding? = null
    private lateinit var creditsAdapter: CreditsAdapter
    private val args: PersonFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPersonBinding.inflate(layoutInflater, container, false)
        binding?.viewmodel = viewModel
        binding?.lifecycleOwner = viewLifecycleOwner

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        creditsAdapter = CreditsAdapter()

        binding?.recyclerCredits?.apply {
            adapter = creditsAdapter
            layoutManager = LinearLayoutManager(requireActivity())
            isNestedScrollingEnabled = true

            addItemDecoration(
                MarginItemVerticalDecoration(
                    0,
                    resources.getDimension(R.dimen.recyclerview_margin_vertical).toInt()
                )
            )
        }

        Timber.d("Person ID = ${args.personId}")

        viewModel.fetchMovieCredits(args.personId)
        viewModel.fetchTvCredits(args.personId)
        viewModel.fetchPersonDetails(args.personId)

        viewModel.movieCredits.observe(viewLifecycleOwner) {
            creditsAdapter.setList(it.cast.sortedByDescending { credit -> credit.release_date })
        }

        viewModel.tvCredits.observe(viewLifecycleOwner) {

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}