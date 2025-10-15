package work.aljazroglic.kino

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import work.aljazroglic.kino.databinding.ActivityMainBinding
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = binding.root

        binding.infoButton.setOnClickListener {
            Log.i("UPORA", "Not implemented")
        }

        binding.aboutButton.setOnClickListener {
            Log.i("UPORA", "Not implemented")
        }

        binding.addButton.setOnClickListener {
            Log.i("UPORA", "Not implemented")
        }

        binding.exitButton.setOnClickListener {
            finishAffinity()
            exitProcess(0)
        }

        setContentView(view)
    }
}