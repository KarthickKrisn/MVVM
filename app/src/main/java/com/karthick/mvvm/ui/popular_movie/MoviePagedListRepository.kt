package com.karthick.mvvm.ui.popular_movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.karthick.mvvm.data.api.POST_PER_PAGE
import com.karthick.mvvm.data.api.TheMovieDBInterface
import com.karthick.mvvm.data.repository.MovieDataSource
import com.karthick.mvvm.data.repository.MovieDataSourceFactory
import com.karthick.mvvm.data.repository.NetworkState
import com.karthick.mvvm.data.vo.Movie
import io.reactivex.disposables.CompositeDisposable

class MoviePagedListRepository (private val apiService : TheMovieDBInterface) {

    lateinit var moviePagedList : LiveData<PagedList<Movie>>
    lateinit var movieDataSourceFactory : MovieDataSourceFactory

    fun fetchLiveMoviePageList (compositeDisposable : CompositeDisposable): LiveData<PagedList<Movie>>{

        movieDataSourceFactory =  MovieDataSourceFactory(apiService,compositeDisposable)

        val config:PagedList.Config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(POST_PER_PAGE)
            .build()

        moviePagedList = LivePagedListBuilder(movieDataSourceFactory,config).build()

        return moviePagedList
    }

    fun getNetworkState(): LiveData<NetworkState>{
        return Transformations.switchMap<MovieDataSource,NetworkState>(
            movieDataSourceFactory.movieLiveDataSource, MovieDataSource::networkState
        )
    }
}