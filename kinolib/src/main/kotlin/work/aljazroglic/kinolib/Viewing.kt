@file:OptIn(ExperimentalUuidApi::class)

package work.aljazroglic.kinolib

import kotlinx.serialization.Serializable
import kotlin.random.Random
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Serializable
class Viewing(val hall: Hall, val movie: MovieBase, val ticketPrice: Double, var filledCapacity: Int, val uuid: Uuid = Uuid.random()) {
    override fun toString(): String {
        return "$hall; Filled: $filledCapacity; $movie; Viewing price: $ticketPrice"
    }

    companion object {
        fun generateViewings(size: Int, halls: List<Hall>, movies: List<MovieBase>) : List<Viewing> {
            return List(size) {
                val hall = halls.random()
                Viewing(hall, movies.random(), Random.nextDouble(10.0, 20.0), (0..hall.capacity).random())
            }
        }
    }
}