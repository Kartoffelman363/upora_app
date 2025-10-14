package work.aljazroglic.kinolib

import java.util.Date
import kotlin.random.Random

class Viewing(val hall: Hall, val movie: Movie, val ticketPrice: Double, val viewingDate: Date, filledCapacity: Int) : Comparable<Viewing> {
    private var _filledCapacity = 0
    var filledCapacity: Int
        get() {
            return _filledCapacity
        }
        set(value) {
            if (value < 0 || value > hall.capacity) {
                throw IllegalArgumentException("Value $value out of range (0, ${hall.capacity})")
            } else {
                _filledCapacity = value
            }
        }

    override fun toString(): String {
        return "$hall; Filled: $filledCapacity; $movie; Viewing price: $ticketPrice; Date: $viewingDate"
    }

    override fun compareTo(other: Viewing): Int {
        return this.viewingDate.compareTo(other.viewingDate);
    }

    companion object {
        fun generateViewings(size: Int, halls: List<Hall>, movies: List<Movie>) : List<Viewing> {
            return List(size) {
                val hall = halls.random()
                Viewing(hall, movies.random(), Random.nextDouble(10.0, 20.0), Date(), (0..hall.capacity).random())
            }
        }
    }
}