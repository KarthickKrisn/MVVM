package com.karthick.mvvm.ui.popular_movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.karthick.mvvm.data.repository.NetworkState
import com.karthick.mvvm.data.vo.Movie
import com.karthick.mvvm.ui.popular_movie.MoviePagedListRepository
import io.reactivex.disposables.CompositeDisposable

class MainActivityViewModel (private val movieRepository : MoviePagedListRepository): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val moviePageList : LiveData<PagedList<Movie>> by lazy {
        movieRepository.fetchLiveMoviePageList(compositeDisposable)
    }

    val networkState : LiveData<NetworkState> by lazy {
        movieRepository.getNetworkState()
    }

    fun listIsEmpty(): Boolean{
        return moviePageList.value?.isEmpty() ?: true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}