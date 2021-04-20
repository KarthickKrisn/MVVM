package com.karthick.mvvm.data.api

import com.karthick.mvvm.data.vo.MovieDetails
import com.karthick.mvvm.data.vo.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDBInterface {
    //https://api.themoviedb.org/3/movie/popular?api_key=ff545a6a28b10855e59e7a38bd4fda8d&page=1
    //https://api.themoviedb.org/3/movie/550?api_key=ff545a6a28b10855e59e7a38bd4fda8d
    // https://image.tmdb.org/t/p/w342/or06FN3Dka5tukK1e9sl16pB3iy.jpg

    @GET("movie/popular")
    fun getPopularMovie(@Query("page")page : Int): Single<MovieResponse>
    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id")id : Int): Single<MovieDetails>


}