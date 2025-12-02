@file:OptIn(ExperimentalUuidApi::class)

package work.aljazroglic.kino

import android.app.Application
import work.aljazroglic.kinolib.Theatre
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class MyApplication: Application() {
    val theatre = Theatre.generateRandom()
    val userUuid: Uuid = Uuid.random()
}