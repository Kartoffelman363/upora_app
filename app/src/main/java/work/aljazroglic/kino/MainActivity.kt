@file:OptIn(ExperimentalUuidApi::class)

package work.aljazroglic.kino

import android.content.Intent
import android.os.Bundle
import android.util.Log
import work.aljazroglic.kino.databinding.ActivityMainBinding
import kotlin.uuid.ExperimentalUuidApi

class MainActivity : BaseActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val theatre = app.theatre

        binding.buttonInfo.setOnClickListener {
            Log.i("UPORA", getString(R.string.movies_log, theatre.movies.count()))
            Log.i("UPORA", "${theatre.movies}")
            Log.i("UPORA", "${app.sharedPreferences.all}")
        }

        binding.buttonAbout.setOnClickListener {
            val aboutIntent = Intent(this, AboutActivity::class.java)
            startActivity(aboutIntent)
        }

        binding.buttonOpenMovieOverview.setOnClickListener {
            val movieOverviewIntent = Intent(this, MovieOverviewActivity::class.java)
            startActivity(movieOverviewIntent)
        }

        binding.buttonExit.setOnClickListener {
            finish()
        }

        setContentView(binding.root)
    }

    override fun onStop() {
        super.onStop()
        app.writeTheatreToFile()
    }
}