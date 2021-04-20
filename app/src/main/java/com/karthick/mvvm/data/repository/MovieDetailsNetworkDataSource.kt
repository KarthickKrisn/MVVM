package com.karthick.mvvm.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.karthick.mvvm.data.api.TheMovieDBInterface
import com.karthick.mvvm.data.vo.MovieDetails
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.Exception

class MovieDetailsNetworkDataSource(
        private val apiService: TheMovieDBInterface,
        private val compositeDisposable: CompositeDisposable
) {

    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get() = _networkState

    private val _downloadedMovieDetailsResponse = MutableLiveData<MovieDetails>()
    val downloadedMovieDetailsResponse: LiveData<MovieDetails>
        get() = _downloadedMovieDetailsResponse


    fun fetchMovieDetails(movieId: Int) {

        _networkState.postValue(NetworkState.LOADING)

        try {

            compositeDisposable.add(
                apiService.getMovieDetails(movieId)
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                            _downloadedMovieDetailsResponse.postValue(it)
                            _networkState.postValue(NetworkState.LOADED)
                        }, {
                            _networkState.postValue(NetworkState.ERROR)
                            Log.e("appSample", "MovieDetailsDataSource" + it.message)
                        }
                    )
            )
        } catch (e: Exception) {
            Log.e("appSample", "MovieDetailsDataSource" + e.message)

        }
    }
}