package com.example.movieapi.movies

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.model.MoviesItemData

class MyDiffCallback(
    var movies: MutableList<MoviesItemData>,
    var moviesFilter: MutableList<MoviesItemData>
): DiffUtil.Callback() {

    override fun getOldListSize() = movies.size

    override fun getNewListSize() = moviesFilter.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        movies[oldItemPosition].title.equals(moviesFilter[newItemPosition].title)

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        movies[oldItemPosition] == moviesFilter[newItemPosition]

}