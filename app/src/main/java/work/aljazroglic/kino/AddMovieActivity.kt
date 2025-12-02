@file:OptIn(ExperimentalUuidApi::class)

package work.aljazroglic.kino

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

        binding.buttonAdd.setOnClickListener {
            val theatre = app.theatre
            val name = binding.editTextMovieName.text.toString()
            val rating = binding.editTextNumberRating.text.toString().toIntOrNull()
            if (name.isBlank()) {
                return@setOnClickListener
            }
            if (rating != null && (rating >= 0 && rating <= 10)) {
                theatre.movies += RatedMovie(name, rating)
            }
            else {
                theatre.movies += Movie(name)
            }
            finish()
        }

        binding.buttonCancel.setOnClickListener {
            finish()
        }

        binding.editTextMovieName.setText("")
        binding.editTextNumberRating.setText("")
    }
}