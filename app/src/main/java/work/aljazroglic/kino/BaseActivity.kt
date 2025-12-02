package work.aljazroglic.kino

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {
    lateinit var app: MyApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = application as MyApplication
    }

    override fun onStart() {
        super.onStart()
        val key = "${localClassName}_on_start_count"
        app.bumpStat(key)
    }
}