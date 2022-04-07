package com.mendelin.tmdb_koin.ui.movies

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mendelin.tmdb_koin.ui.movies_now_playing.MoviesNowPlayingFragment
import com.mendelin.tmdb_koin.ui.movies_popular.MoviesPopularFragment
import com.mendelin.tmdb_koin.ui.movies_upcoming.MoviesUpcomingFragment

class MoviesAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
       return when (position) {
            0 -> MoviesNowPlayingFragment()
            1 -> MoviesPopularFragment()
            else -> MoviesUpcomingFragment()
        }
    }
}