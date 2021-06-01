package com.fintecimal.testingespresso.data.source

import com.fintecimal.testingespresso.data.Movie


interface MoviesDataSource {

    fun getMovie(movieId: Int): Movie?
}