package com.fintecimal.testingespresso.ui.main.movie

import com.codingwithmitch.espressouitestexamples.ui.movie.DirectorsFragmentTest
import com.codingwithmitch.espressouitestexamples.ui.movie.MovieDetailFragmentTest
import com.codingwithmitch.espressouitestexamples.ui.movie.StarActorsFragmentTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    MovieDetailFragmentTest::class,
    DirectorsFragmentTest::class,
    StarActorsFragmentTest::class
)
class MovieFragmentTestSuite