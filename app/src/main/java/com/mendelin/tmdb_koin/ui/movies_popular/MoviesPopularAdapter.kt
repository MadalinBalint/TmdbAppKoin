package com.mendelin.tmdb_koin.ui.movies_popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mendelin.tmdb_koin.ItemMovieListResultBinding
import com.mendelin.tmdb_koin.R
import com.mendelin.tmdb_koin.base.IDetails
import com.mendelin.tmdb_koin.data.model.entity.MovieListResultItem

class MoviesPopularAdapter : PagingDataAdapter<MovieListResultItem, MoviesPopularAdapter.PopularMoviesViewHolder>(PopulargMoviesDiffCallBack()) {
    class PopularMoviesViewHolder(var binding: ItemMovieListResultBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieListResultItem) {
            binding.property = movie

            binding.callback = IDetails {
                val navController = Navigation.findNavController(binding.root)

                val args = Bundle()
                args.putInt("movieId", movie.id)

                navController.navigate(R.id.movieDetailsFragment, args)
            }

            binding.executePendingBindings()
        }
    }

    class PopulargMoviesDiffCallBack : DiffUtil.ItemCallback<MovieListResultItem>() {
        override fun areItemsTheSame(oldItem: MovieListResultItem, newItem: MovieListResultItem): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: MovieListResultItem, newItem: MovieListResultItem): Boolean {
            return oldItem == newItem
        }

    }

    override fun onBindViewHolder(holder: PopularMoviesViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMoviesViewHolder {
        return PopularMoviesViewHolder(ItemMovieListResultBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
}