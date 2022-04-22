package com.mendelin.tmdb_koin.ui.tv_shows_top_rated

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mendelin.tmdb_koin.ItemTvListResultBinding
import com.mendelin.tmdb_koin.R
import com.mendelin.tmdb_koin.common.IDetails
import com.mendelin.tmdb_koin.data.model.entity.TvListResultItem
import com.mendelin.tmdb_koin.ui.favorites.FavoritesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent

class TvTopRatedAdapter : PagingDataAdapter<TvListResultItem, TvTopRatedAdapter.TvTopRatedViewHolder>(TvPopularDiffCallBack()) {
    class TvTopRatedViewHolder(var binding: ItemTvListResultBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewmodel by KoinJavaComponent.inject<FavoritesViewModel>(FavoritesViewModel::class.java)

        fun bind(tvShow: TvListResultItem) {
            binding.property = tvShow

            binding.callback = IDetails {
                val args = Bundle()
                args.putString("tvShowName", tvShow.name)
                args.putInt("tvShowId", tvShow.id)

                binding.tvListCard.findNavController().navigate(R.id.tvShowDetailsFragment, args)
            }

            binding.btnFavoriteTvShow.isChecked = viewmodel.isFavoriteTvShow(tvShow.id)

            binding.btnFavoriteTvShow.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    viewmodel.insertFavoriteTvShow(tvShow)
                }
            }

            binding.executePendingBindings()
        }
    }

    class TvPopularDiffCallBack : DiffUtil.ItemCallback<TvListResultItem>() {
        override fun areItemsTheSame(oldItem: TvListResultItem, newItem: TvListResultItem): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: TvListResultItem, newItem: TvListResultItem): Boolean {
            return oldItem == newItem
        }

    }

    override fun onBindViewHolder(holder: TvTopRatedViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvTopRatedViewHolder {
        return TvTopRatedViewHolder(ItemTvListResultBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
}