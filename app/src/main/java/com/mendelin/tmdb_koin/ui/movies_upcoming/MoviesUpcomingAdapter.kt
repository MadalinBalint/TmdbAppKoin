package com.mendelin.tmdb_koin.ui.movies_upcoming

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mendelin.tmdb_koin.ItemMovieListResultBinding
import com.mendelin.tmdb_koin.R
import com.mendelin.tmdb_koin.base.IDetails
import com.mendelin.tmdb_koin.data.model.entity.MovieListResultItem

class MoviesUpcomingAdapter : PagingDataAdapter<MovieListResultItem, MoviesUpcomingAdapter.UpcomingMoviesViewHolder>(UpcomingMoviesDiffCallBack()) {
    class UpcomingMoviesViewHolder(var binding: ItemMovieListResultBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieListResultItem) {
            binding.property = movie

            binding.callback = IDetails {
                val args = Bundle()
                args.putInt("movieId", movie.id)

                binding.homeCard.findNavController().navigate(R.id.movieDetailsFragment, args)
            }

            binding.executePendingBindings()
        }
    }

    class UpcomingMoviesDiffCallBack : DiffUtil.ItemCallback<MovieListResultItem>() {
        override fun areItemsTheSame(oldItem: MovieListResultItem, newItem: MovieListResultItem): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: MovieListResultItem, newItem: MovieListResultItem): Boolean {
            return oldItem == newItem
        }

    }

    override fun onBindViewHolder(holder: UpcomingMoviesViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingMoviesViewHolder {
        return UpcomingMoviesViewHolder(ItemMovieListResultBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
}