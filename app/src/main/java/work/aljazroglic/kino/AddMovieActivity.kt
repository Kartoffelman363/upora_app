@file:OptIn(ExperimentalUuidApi::class)

package work.aljazroglic.kino

import android.content.Intent
import android.os.Bundle
import work.aljazroglic.kino.databinding.ActivityAddMovieBinding
import work.aljazroglic.kinolib.RatedMovie
import work.aljazroglic.kinolib.Movie
import kotlin.uuid.ExperimentalUuidApi

class AddMovieActivity : BaseActivity() {
    private val binding: ActivityAddMovieBinding by lazy {
        ActivityAddMovieBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        app = application as MyApplication

        setContentView(binding.root)

        val movieId = intent.getStringExtra(MOVIE_ID)
        var movie =
            if(movieId != null) app.theatre.movies.find { m -> m.id.toString() == movieId }
            else null

        binding.buttonAdd.setOnClickListener {
            val theatre = app.theatre
            val name = binding.editTextMovieName.text.toString()
            val rating = binding.editTextNumberRating.text.toString().toIntOrNull()
            if (name.isBlank()) {
                return@setOnClickListener
            }
            if (movie != null) {
                movie!!.name = name
                when(movie) {
                    is RatedMovie -> (movie as RatedMovie).rating = rating ?: 0
                    else -> {}
                }
            }
            else {
                if (rating != null && (rating >= 0 && rating <= 10)) {
                    val mov = RatedMovie(name, rating = rating)
                    theatre.movies += mov
                    movie = mov
                }
                else {
                    val mov = Movie(name)
                    theatre.movies += mov
                    movie = mov
                }
            }
            val resultIntent = Intent()
            resultIntent.putExtra(MOVIE_ID, movie.id.toString())

            setResult(RESULT_OK, resultIntent)
            finish()
        }

        binding.buttonCancel.setOnClickListener {
            finish()
        }

        binding.editTextMovieName.setText(movie?.name ?: "")
        when(movie) {
            is RatedMovie -> binding.editTextNumberRating.setText((movie as RatedMovie).rating)
            else -> binding.editTextNumberRating.setText("")
        }
    }

    companion object {
        const val MOVIE_ID = "movie_id"
    }
}