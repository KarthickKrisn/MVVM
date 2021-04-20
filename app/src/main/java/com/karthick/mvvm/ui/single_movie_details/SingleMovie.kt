package com.karthick.mvvm.ui.single_movie_details

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.karthick.mvvm.R
import com.karthick.mvvm.data.api.POSTER_BASE_URL
import com.karthick.mvvm.data.api.TheMovieDBClient
import com.karthick.mvvm.data.api.TheMovieDBInterface
import com.karthick.mvvm.data.repository.NetworkState
import com.karthick.mvvm.data.vo.MovieDetails
import kotlinx.android.synthetic.main.activity_single_movie.*
import java.text.NumberFormat
import java.util.*

class SingleMovie : AppCompatActivity() {

    private lateinit var viewModel: SingleMovieViewModel
    private lateinit var movieViewRepository: MovieDetailsRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_movie)

       // val movieId: Int = 1
        val movieId: Int = intent.getIntExtra("id",1)


        val apiService : TheMovieDBInterface = TheMovieDBClient.getClient()
        movieViewRepository = MovieDetailsRepository(apiService)

        viewModel = getViewModel(movieId)

        viewModel.movieDetails.observe(this, Observer {
            bindUI(it)
        })

        viewModel.networkState.observe(this, Observer {
            progress_bar.visibility = if (it == NetworkState.LOADING) View.VISIBLE else View.GONE
            txt_error.visibility =  if (it == NetworkState.ERROR) View.VISIBLE else View.GONE
        })
    }

    fun bindUI(it:MovieDetails){

        val Bold = Typeface.createFromAsset(assets, "Lato-Bold.ttf")
        val Regular = Typeface.createFromAsset(assets, "Lato-Regular.ttf")
        movie_title.text = it.title
        movie_tagline.text = it.tagline
        movie_release_date.text = it.releaseDate
        movie_rating.text = it.rating.toString()
        movie_runtime.text = it.runtime.toString() + " minutes"
        movie_overview.text = it.overview

        val formatCurrency = NumberFormat.getCurrencyInstance(Locale.US)
        movie_budget.text = formatCurrency.format(it.budget)
        movie_revenue.text = formatCurrency.format(it.revenue)

        movie_title.typeface = Bold
        movie_tagline.typeface = Regular
        movie_release_date.typeface = Regular
        movie_rating.typeface = Regular
        movie_runtime.typeface = Regular
        movie_overview.typeface = Regular
        movie_budget.typeface = Regular
        movie_revenue.typeface = Regular

        txt_release_date.typeface = Bold
        txt_rating.typeface = Bold
        txt_runtime.typeface = Bold
        txt_overview.typeface = Bold
        txt_budget.typeface = Bold
        txt_revenue.typeface = Bold


        val moviePosterURL = POSTER_BASE_URL + it.posterPath
        Glide.with(this)
            .load(moviePosterURL)
            .into(iv_movie_poster);


    }



    private fun getViewModel(movieId:Int) : SingleMovieViewModel {

        return ViewModelProviders.of(this,object :ViewModelProvider.Factory {

            override fun <T : ViewModel?> create(modelClass: Class<T> ):T {
                return SingleMovieViewModel(movieViewRepository,movieId) as T
            }
        })[SingleMovieViewModel::class.java]
    }


}