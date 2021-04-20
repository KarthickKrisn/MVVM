package com.karthick.mvvm.data.vo


import com.google.gson.annotations.SerializedName

data class MovieDetails(
    // val adult: Boolean,
    // @SerializedName("backdrop_path")
    //val backdropPath: String,
    val budget: Int,
    //  val homepage: String,
    val id: Int,
    // @SerializedName("imdb_id")
    // val imdbId: String,
    // @SerializedName("original_language")
    // val originalLanguage: String,
    //  @SerializedName("original_title")
    //  val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    /* @SerializedName("spoken_languages")
     val spokenLanguages: List<SpokenLanguage>,*/
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    @SerializedName("vote_average")
    val rating: Double,
    /*  @SerializedName("vote_count")
      val voteCount: Int*/


)