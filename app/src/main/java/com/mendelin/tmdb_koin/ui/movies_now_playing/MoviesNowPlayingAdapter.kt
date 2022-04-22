package com.mendelin.tmdb_koin.ui.movies_now_playing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mendelin.tmdb_koin.ItemMovieListResultBinding
import com.mendelin.tmdb_koin.R
import com.mendelin.tmdb_koin.common.IDetails
import com.mendelin.tmdb_koin.data.model.entity.MovieListResultItem
import com.mendelin.tmdb_koin.ui.favorites.FavoritesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class MoviesNowPlayingAdapter : PagingDataAdapter<MovieListResultItem, MoviesNowPlayingAdapter.NowPlayingMoviesViewHolder>(NowPlayingMoviesDiffCallBack()) {
    class NowPlayingMoviesViewHolder(var binding: ItemMovieListResultBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewmodel by inject<FavoritesViewModel>(FavoritesViewModel::class.java)

        fun bind(movie: MovieListResultItem) {
            binding.property = movie
            binding.callback = IDetails {
                val navController = Navigation.findNavController(binding.root)

                val args = Bundle()
                args.putInt("movieId", movie.id)

                navController.navigate(R.id.movieDetailsFragment, args)
            }

            binding.btnFavoriteMovie.isChecked = viewmodel.isFavoriteMovie(movie.id)

            binding.btnFavoriteMovie.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    viewmodel.insertFavoriteMovie(movie)
                }
            }

            binding.executePendingBindings()
        }
    }

    class NowPlayingMoviesDiffCallBack : DiffUtil.ItemCallback<MovieListResultItem>() {
        override fun areItemsTheSame(oldItem: MovieListResultItem, newItem: MovieListResultItem): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: MovieListResultItem, newItem: MovieListResultItem): Boolean {
            return oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: NowPlayingMoviesViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingMoviesViewHolder {
        return NowPlayingMoviesViewHolder(ItemMovieListResultBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
}