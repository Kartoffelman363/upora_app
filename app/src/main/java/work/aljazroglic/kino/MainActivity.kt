@file:OptIn(ExperimentalUuidApi::class)

package work.aljazroglic.kino

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import work.aljazroglic.kino.databinding.ActivityMainBinding
import kotlin.uuid.ExperimentalUuidApi

class MainActivity : AppCompatActivity() {
    private lateinit var app: MyApplication

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        app = application as MyApplication

        val theatre = app.theatre

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

    fun addMovieOnClickListener() {
        if (app.theatre.halls.isEmpty()) {
            Log.e("UPORA", getString(R.string.cannot_start_activity_log))
            return
        }
        val addIntent = Intent(this, AddMovieActivity::class.java)
        startActivity(addIntent)
    }
}