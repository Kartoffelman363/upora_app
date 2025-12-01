package work.aljazroglic.kino

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import work.aljazroglic.kino.databinding.ActivityMainBinding
import work.aljazroglic.kinolib.Movie
import work.aljazroglic.kinolib.RatedMovie
import work.aljazroglic.kinolib.Theatre

class MainActivity : AppCompatActivity() {
    private lateinit var theatre: Theatre

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        theatre = Theatre.generateRandom()

        super.onCreate(savedInstanceState)

        binding.buttonInfo.setOnClickListener {
            Log.i("UPORA", getString(R.string.movies_log, theatre.movies.count()))
            Log.i("UPORA", "${theatre.movies}")
        }

        binding.buttonAbout.setOnClickListener {
            val aboutIntent = Intent(this, AboutActivity::class.java)
            startActivity(aboutIntent)
        }

        binding.buttonAddMovie.setOnClickListener { addMovieOnClickListener() }

        binding.buttonExit.setOnClickListener {
            finish()
        }

        setContentView(binding.root)
    }

    val addMovieCallback = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            if (data == null) return@registerForActivityResult
            val name = data.getStringExtra(AddMovieActivity.EXTRA_MOVIE_NAME)
            val rating = data.getStringExtra(AddMovieActivity.EXTRA_MOVIE_RATING)?.toIntOrNull()
            if (name == null) return@registerForActivityResult
            theatre.movies = theatre.movies + if (rating == null) {
                Movie(name)
            } else {
                RatedMovie(name, rating)
            }
        }
    }

    fun addMovieOnClickListener() {
        if (theatre.halls.isEmpty()) {
            Log.e("UPORA", getString(R.string.cannot_start_activity_log))
            return
        }
        val addIntent = Intent(this, AddMovieActivity::class.java)
        addMovieCallback.launch(addIntent)
    }
}