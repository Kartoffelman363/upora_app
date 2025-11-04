package work.aljazroglic.kino

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import work.aljazroglic.kino.databinding.ActivityAddMovieBinding

class AddMovieActivity : AppCompatActivity() {

    private val binding: ActivityAddMovieBinding by lazy {
        ActivityAddMovieBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonAdd.setOnClickListener {
            val name = binding.editTextMovieName.text.toString()
            var rating = binding.editTextNumberRating.text.toString().toIntOrNull()
            if (name.isBlank()) {
                return@setOnClickListener
            }
            if (rating != null && (rating < 0 || rating > 10)) rating = null
            val intent = Intent()
            intent.putExtra(EXTRA_MOVIE_NAME, name)
            intent.putExtra(EXTRA_MOVIE_RATING, rating)
            setResult(RESULT_OK, intent)
            finish()
        }

        binding.buttonCancel.setOnClickListener {
            finish()
        }
    }

    companion object {
        const val EXTRA_MOVIE_NAME = "work.aljazroglic.kino.MOVIE_NAME"
        const val EXTRA_MOVIE_RATING = "work.aljazroglic.kino.MOVIE_RATING"
    }
}