@file:OptIn(ExperimentalUuidApi::class)

package work.aljazroglic.kinolib

import java.util.Date
import kotlin.random.Random
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class Viewing(val hall: Hall, val movie: MovieBase, val ticketPrice: Double, val viewingDate: Date, var filledCapacity: Int, val uuid: Uuid = Uuid.random()) : Comparable<Viewing> {
    override fun toString(): String {
        return "$hall; Filled: $filledCapacity; $movie; Viewing price: $ticketPrice; Date: $viewingDate"
    }

    override fun compareTo(other: Viewing): Int {
        return this.viewingDate.compareTo(other.viewingDate)
    }

    companion object {
        fun generateViewings(size: Int, halls: List<Hall>, movies: List<MovieBase>) : List<Viewing> {
            return List(size) {
                val hall = halls.random()
                Viewing(hall, movies.random(), Random.nextDouble(10.0, 20.0), Date(), (0..hall.capacity).random())
            }
        }
    }
}