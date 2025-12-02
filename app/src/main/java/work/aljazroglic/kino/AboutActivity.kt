@file:OptIn(ExperimentalUuidApi::class)

package work.aljazroglic.kino

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import work.aljazroglic.kino.databinding.ActivityAboutBinding
import kotlin.uuid.ExperimentalUuidApi

class AboutActivity : AppCompatActivity() {
    lateinit var app: MyApplication

    private val binding: ActivityAboutBinding by lazy {
        ActivityAboutBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        app = application as MyApplication

        enableEdgeToEdge()

        binding.imageButtonReturn.setOnClickListener {
            finish()
        }

        binding.textViewUserIDValue.text = app.userUuid.toString()

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}