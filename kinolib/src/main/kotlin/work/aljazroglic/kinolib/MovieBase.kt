package work.aljazroglic.kinolib

import kotlinx.serialization.Serializable

@Serializable
sealed class MovieBase {
    abstract val name: String
}