package com.mendelin.tmdb_koin.ui.tv_show_seasons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.mendelin.tmdb_koin.R
import com.mendelin.tmdb_koin.databinding.FragmentTvShowSeasonsBinding
import com.mendelin.tmdb_koin.ui.custom_view.MarginItemVerticalDecoration
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber

class TvShowSeasonsFragment : Fragment() {
    private val viewModel by sharedViewModel<TvShowSeasonsViewModel>()
    private var binding: FragmentTvShowSeasonsBinding? = null
    private lateinit var seasonsAdapter: SeasonsAdapter
    private val args: TvShowSeasonsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTvShowSeasonsBinding.inflate(layoutInflater, container, false)
        binding?.viewmodel = viewModel
        binding?.lifecycleOwner = viewLifecycleOwner

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        seasonsAdapter = SeasonsAdapter(args.tvShowName, args.tvShowId)

        binding?.recyclerSeasons?.apply {
            adapter = seasonsAdapter
            layoutManager = LinearLayoutManager(requireActivity())
            isNestedScrollingEnabled = true

            addItemDecoration(
                MarginItemVerticalDecoration(
                    resources.getDimension(R.dimen.recyclerview_margin_vertical).toInt(),
                    resources.getDimension(R.dimen.recyclerview_margin_vertical).toInt()
                )
            )
        }

        Timber.d("TV Show ID = ${args.tvShowId}")

        viewModel.fetchTvShowDetails(args.tvShowId)
        viewModel.fetchTvShowCredits(args.tvShowId)

        viewModel.details.observe(viewLifecycleOwner) {
            Timber.d(it.toString())
        }

        viewModel.credits.observe(viewLifecycleOwner) {
            Timber.d(it.toString())
        }

        viewModel.seasons.observe(viewLifecycleOwner) {
            Timber.d(it.toString())

            seasonsAdapter.setList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}