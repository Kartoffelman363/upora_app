package work.aljazroglic.kinolib

import kotlinx.serialization.Serializable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Serializable
sealed class MovieBase {
    @OptIn(ExperimentalUuidApi::class)
    abstract val id: Uuid
    abstract var name: String
}