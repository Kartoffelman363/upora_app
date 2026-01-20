@file:OptIn(ExperimentalUuidApi::class)

package work.aljazroglic.kino

import android.app.Application
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ProcessLifecycleOwner
import kotlinx.serialization.json.Json
import work.aljazroglic.kinolib.Theatre
import java.io.File
import java.lang.Exception
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class MyApplication: Application(), LifecycleObserver {
    lateinit var theatre: Theatre
    private lateinit var theatreFile: File
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate() {
        super.onCreate()
        sharedPreferences = getSharedPreferences(SP_FILE_NAME, MODE_PRIVATE)
        if (!sharedPreferences.contains(USER_UUID)) {
            saveUserUUID(Uuid.random())
        }
        theatreFile = File(filesDir, THEATRE_FILE_NAME)
        theatre = readTheatreFromFile()

        val lifecycleEventObserver = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_STOP -> {
                    bumpStat("app_to_background")
                }
                else -> {}
            }
        }

        ProcessLifecycleOwner.get().lifecycle.addObserver(lifecycleEventObserver)
    }

    fun bumpStat(key: String) {
        val onStartCount = sharedPreferences.getInt(key, 0) + 1
        sharedPreferences.edit {
            putInt(key, onStartCount)
        }
    }

    fun readTheatreFromFile(): Theatre {
        try {
            val theatreJsonString = theatreFile.readText()
            return Json.decodeFromString(theatreJsonString)
        } catch(_: Exception) {
            return Theatre.generateRandom()
        }
    }

    fun writeTheatreToFile() {
        theatreFile.writeText(Json.encodeToString(theatre))
    }

    fun saveUserUUID(userUuid: Uuid) {
        sharedPreferences.edit {
            putString(USER_UUID, userUuid.toString())
        }
    }

    fun getUserUUID(): String? {
        return sharedPreferences.getString(USER_UUID, null)
    }

    companion object {
        const val THEATRE_FILE_NAME = "Theatre.json"
        const val SP_FILE_NAME = "MyApplicationSharedPref.data"
        const val USER_UUID = "USER_UUID"
    }
}