package com.mendelin.tmdb_koin.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mendelin.tmdb_koin.ItemMovieListResultBinding
import com.mendelin.tmdb_koin.ItemTvListResultBinding
import com.mendelin.tmdb_koin.R
import com.mendelin.tmdb_koin.common.FavoriteType
import com.mendelin.tmdb_koin.common.IDetails
import com.mendelin.tmdb_koin.data.model.entity.MovieListResultItem
import com.mendelin.tmdb_koin.data.model.entity.MultipleItem
import com.mendelin.tmdb_koin.data.model.entity.TvListResultItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class FavoritesAdapter : ListAdapter<MultipleItem, FavoritesAdapter.MultipleViewHolder>(MultipleDiffCallback()) {
    private val favoritesList: ArrayList<MultipleItem> = ArrayList()

    class MultipleViewHolder(var binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewmodel by inject<FavoritesViewModel>(FavoritesViewModel::class.java)

        fun bindMovie(movie: MovieListResultItem) {
            (binding as ItemMovieListResultBinding).apply {
                property = movie
                callback = IDetails {
                    val args = Bundle()
                    args.putInt("movieId", movie.id)

                    movieCard.findNavController().navigate(R.id.movieDetailsFragment, args)
                }

                btnFavoriteMovie.isChecked = true

                btnFavoriteMovie.setOnCheckedChangeListener { _, check ->
                    CoroutineScope(Dispatchers.IO).launch {
                        viewmodel.deleteFavoriteMovie(movie.id)
                        delay(200)
                        viewmodel.fetchFavoritesList()
                    }
                }

                executePendingBindings()
            }
        }

        fun bindTvShow(tvShow: TvListResultItem) {
            (binding as ItemTvListResultBinding).apply {
                property = tvShow

                callback = IDetails {
                    val args = Bundle()
                    args.putString("tvShowName", tvShow.name)
                    args.putInt("tvShowId", tvShow.id)

                    tvListCard.findNavController().navigate(R.id.tvShowDetailsFragment, args)
                }

                btnFavoriteTvShow.isChecked = true

                btnFavoriteTvShow.setOnCheckedChangeListener { _, check ->
                    CoroutineScope(Dispatchers.IO).launch {
                        viewmodel.deleteFavoriteTvShow(tvShow.id)
                        delay(200)
                        viewmodel.fetchFavoritesList()
                    }
                }

                executePendingBindings()
            }
        }
    }

    class MultipleDiffCallback : DiffUtil.ItemCallback<MultipleItem>() {
        override fun areItemsTheSame(oldItem: MultipleItem, newItem: MultipleItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: MultipleItem, newItem: MultipleItem): Boolean {
            return oldItem.content == newItem.content
        }
    }

    override fun getItemViewType(position: Int): Int {
        return favoritesList[position].type.value
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MultipleViewHolder {
        return when (viewType) {
            FavoriteType.FAVORITE_MOVIE.value ->
                MultipleViewHolder(ItemMovieListResultBinding.inflate(LayoutInflater.from(parent.context), parent, false))

            FavoriteType.FAVORITE_TV_SHOW.value ->
                MultipleViewHolder(ItemTvListResultBinding.inflate(LayoutInflater.from(parent.context), parent, false))

            else -> throw IndexOutOfBoundsException("View type $viewType should be between 0..1")
        }
    }

    override fun onBindViewHolder(holder: MultipleViewHolder, position: Int) {
        when (getItemViewType(position)) {
            FavoriteType.FAVORITE_MOVIE.value ->
                holder.bindMovie(favoritesList[position].content as MovieListResultItem)

            FavoriteType.FAVORITE_TV_SHOW.value ->
                holder.bindTvShow(favoritesList[position].content as TvListResultItem)
        }
    }

    fun setList(list: List<MultipleItem>) {
        favoritesList.apply {
            clear()
            addAll(list)
        }

        submitList(favoritesList)
        notifyDataSetChanged()
    }
}