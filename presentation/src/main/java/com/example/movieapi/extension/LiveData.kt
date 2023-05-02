package com.example.movieapi.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.movieapi.entities.DataHolder
import com.example.movieapi.entities.Status

inline fun<T: Any?> LiveData<DataHolder<T?>>.observeResponse (
    owner: LifecycleOwner,
    crossinline success: ((T?) -> Unit) = {

    },
    crossinline fail: ((String) -> Unit) = {

    },
    crossinline loading: ((String) -> Unit) = {

    }
){
    observe(owner, Observer{ holder: DataHolder<T>? ->
        when(holder?.responseType){
            is Status.SUCCESSFUL -> success(holder.data)
            is Status.ERROR -> fail(holder.error?.message.toString())
            is Status.LOADING -> loading("loading")
        }
    })
}

