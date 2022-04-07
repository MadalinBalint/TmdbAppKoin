package com.mendelin.tmdb_koin.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mendelin.tmdb_koin.databinding.FragmentFavoritesBinding

class FavoritesFragment : Fragment() {
    //private val viewModel by sharedViewModel<PopularMoviesViewModel>()
    private var binding: FragmentFavoritesBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        populateUI()
        observeViewModel()
    }

    private fun setupUI() {

    }

    private fun populateUI() {
        //viewModel.fetchPopularMovies(page = 1)
    }

    private fun observeViewModel() {
        /*viewModel.getPopularMoviesList().observe(viewLifecycleOwner) { list ->
            println("Popular Movies List")
            println("Total pages = ${list.total_pages}")
            println("Total pages = ${list.total_results}")

            //list.results.forEach(::println)
        }*/
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}