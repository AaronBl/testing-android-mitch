package com.codingwithmitch.espressouitestexamples.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.codingwithmitch.espressouitestexamples.data.Movie
import com.codingwithmitch.espressouitestexamples.data.source.MoviesRemoteDataSource
import com.fintecimal.testingespresso.R
import com.fintecimal.testingespresso.ui.movie.DirectorsFragment

class MovieDetailFragment : Fragment(){

    private lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { args ->
            args.getInt("movie_id").let{ movieId ->
                MoviesRemoteDataSource.getMovie(movieId)?.let{ movieFromRemote ->
                    movie = movieFromRemote
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setMovieDetails(view)

        val movieDirectors = view.findViewById<TextView>(R.id.movie_directiors)
        movieDirectors.setOnClickListener {
            val bundle = Bundle()
            bundle.putStringArrayList("args_directors", movie.directors)
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, DirectorsFragment::class.java, bundle)
                ?.addToBackStack("DirectorsFragment")
                ?.commit()
        }

        val movieStarActors = view.findViewById<TextView>(R.id.movie_star_actors)
        movieStarActors.setOnClickListener {
            val bundle = Bundle()
            bundle.putStringArrayList("args_actors", movie.star_actors)
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, StarActorsFragment::class.java, bundle)
                ?.addToBackStack("StarActorsFragment")
                ?.commit()
        }
    }

    private fun setMovieDetails(view: View){
        movie.let{ nonNullMovie ->
            Glide.with(this)
                .load(nonNullMovie.image)
                .into(view.findViewById(R.id.movie_image))
            val movieTitle = view.findViewById<TextView>(R.id.movie_title)
            movieTitle.text = nonNullMovie.title
            val movieDescription = view.findViewById<TextView>(R.id.movie_description)
            movieDescription.text = nonNullMovie.description
        }
    }

}

















