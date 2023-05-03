package com.example.movieapi.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.MoviesItemData
import com.example.movieapi.R

class MoviesListAdapter: RecyclerView.Adapter<MoviesListAdapter.MoviesViewHolder>() , Filterable {

    var moviesListOld = mutableListOf<MoviesItemData>()
    var moviesFilter = mutableListOf<MoviesItemData>()
    var moviesList = mutableListOf<MoviesItemData>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.movies_item,parent,false)
        return MoviesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return moviesListOld.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getFilter(): Filter {
        TODO("Not yet implemented")
    }


    class MoviesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(moviesItemData: MoviesItemData){
            with(itemView){
                textAlignment
            }
        }
    }
}