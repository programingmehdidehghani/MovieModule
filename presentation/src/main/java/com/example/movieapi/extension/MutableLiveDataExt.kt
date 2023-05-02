package com.example.movieapi.extension

import androidx.lifecycle.MutableLiveData
import com.example.movieapi.entities.DataHolder
import com.example.movieapi.entities.Error
import com.example.movieapi.entities.Status
import retrofit2.Response

fun <T: Any> MutableLiveData<DataHolder<T?>>.loadingData() = apply {
    postValue(DataHolder(responseType = Status.LOADING))
}

fun <T: Any> MutableLiveData<DataHolder<T?>>.responseData(responseMethod: Response<T>) = apply {
    responseMethod.apply {
        if (isSuccessful){
             postValue(
                 DataHolder(responseType = Status.SUCCESSFUL, data = this.body())
             )
        } else {
            postValue(
                DataHolder(
                    responseType = Status.ERROR,
                    error = Error("invalid response")
                )
            )
        }
    }
}