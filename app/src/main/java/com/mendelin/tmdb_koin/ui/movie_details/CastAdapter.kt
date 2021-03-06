package com.mendelin.tmdb_koin.ui.movie_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mendelin.tmdb_koin.ItemCastBinding
import com.mendelin.tmdb_koin.R
import com.mendelin.tmdb_koin.common.IDetails
import com.mendelin.tmdb_koin.data.model.entity.CastItem

class CastAdapter(val isMovieCast: Boolean = true) : ListAdapter<CastItem, CastAdapter.CastViewHolder>(CastDiffCallBack()) {
    private val castList: ArrayList<CastItem> = ArrayList()

    class CastViewHolder(var binding: ItemCastBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cast: CastItem) {
            binding.cast = cast
            binding.callback = IDetails {
                val args = Bundle()
                args.putInt("personId", cast.id)

                binding.castCard.findNavController().navigate(R.id.personFragment, args)
            }

            binding.executePendingBindings()
        }
    }

    class CastDiffCallBack : DiffUtil.ItemCallback<CastItem>() {
        override fun areItemsTheSame(oldItem: CastItem, newItem: CastItem): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: CastItem, newItem: CastItem): Boolean {
            return oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        val item = castList[position]
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        return CastViewHolder(ItemCastBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    fun setList(list: List<CastItem>) {
        castList.apply {
            clear()
            addAll(list)
        }

        submitList(castList)
        notifyDataSetChanged()
    }
}