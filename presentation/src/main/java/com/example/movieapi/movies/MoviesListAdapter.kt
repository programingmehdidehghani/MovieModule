package com.example.movieapi.movies

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.model.MoviesItemData
import com.example.movieapi.R
import com.example.movieapi.databinding.MoviesItemBinding
import com.example.movieapi.di.IMAGE_URL
import java.util.Locale

class MoviesListAdapter : RecyclerView.Adapter<MoviesListAdapter.MoviesViewHolder>(), Filterable {

    var moviesListOld = mutableListOf<MoviesItemData>()
    var moviesFilter = mutableListOf<MoviesItemData>()
    var moviesList = mutableListOf<MoviesItemData>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view =
            MoviesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return moviesListOld.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getFilter(): Filter {
        return object : Filter() {

            override fun performFiltering(constraint: CharSequence?): FilterResults {
                moviesListOld.clear()
                moviesListOld.addAll(moviesList)
                val charSearch = constraint.toString()
                moviesFilter = if (charSearch.isEmpty()) {
                    moviesListOld
                } else {
                    val resultList = mutableListOf<MoviesItemData>()
                    for (row in moviesListOld) {
                        if (row.title?.toLowerCase(Locale.ROOT)
                                ?.contains(charSearch.toLowerCase(Locale.ROOT))!!
                        ) {
                            resultList.add(row)
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.count = moviesFilter.size
                filterResults.values = moviesFilter
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                if (!constraint.isNullOrEmpty()) {
                    moviesFilter = results?.values as MutableList<MoviesItemData>
                    val diffResult: DiffUtil.DiffResult =
                    DiffUtil.calculateDiff(MyDiffCallback(moviesListOld,moviesFilter))
                    moviesListOld.clear()
                    moviesListOld.addAll(moviesList)
                    diffResult.dispatchUpdatesTo(this@MoviesListAdapter)
                } else {
                    moviesListOld.clear()
                }
            }

        }
    }


    class MoviesViewHolder(private val binding: MoviesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("CheckResult")
        fun bind(moviesItemData: MoviesItemData) {
            with(itemView) {
                binding.textViewTitle.text = moviesItemData.title
                Glide.with(this).load(IMAGE_URL + moviesItemData.poster_path)

                setOnClickListener {
                    val intent = Intent(context, MoviesDetailActivity::class.java)
                    val bundle = Bundle()
                    bundle.apply {
                        putString(MoviesDetailActivity.BUNDLE_TITLE, moviesItemData.title)
                        putString(
                            MoviesDetailActivity.BUNDLE_AVERAGE,
                            moviesItemData.vote_average.toString()
                        )
                        putString(MoviesDetailActivity.BUNDLE_OVERVIEW, moviesItemData.overview)
                        putString(MoviesDetailActivity.BUNDLE_PATH, moviesItemData.poster_path)
                        putString(
                            MoviesDetailActivity.BUNDLE_POPULARITY,
                            moviesItemData.popularity.toString()
                        )
                    }
                    intent.putExtras(bundle)
                    context.startActivity(intent)
                }
            }
        }
    }
}