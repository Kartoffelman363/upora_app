@file:OptIn(ExperimentalUuidApi::class)

package work.aljazroglic.kinolib

import io.github.serpro69.kfaker.Faker
import kotlin.uuid.ExperimentalUuidApi

class Theatre (val halls: List<Hall> = emptyList(), var movies: List<MovieBase>, val viewings: List<Viewing>) {
    override fun toString(): String {
        return "Viewings:\n\t${viewings.joinToString("\n\t")}"
    }

    fun findMoviesByName(name: String): List<MovieBase>? {
        return this.movies.filter { movie -> movie.name.contains(name) } as List<MovieBase>?
    }

    fun findMoviesByNameCount(name: String): Int {
        return findMoviesByName(name)?.size ?: 0
    }

    fun filterMovies(name: String): List<MovieBase>? {
        return this.movies.filter { movie -> !movie.name.contains(name) } as List<MovieBase>?
    }

    fun averageOccupationOfViewings(): Double {
        if (viewings.isEmpty()) return 0.0
        return viewings.fold (0.0) {d, viewing -> d + viewing.filledCapacity / viewing.hall.capacity } / viewings.size
    }

    fun firstTenHallsWithNamesLongerThanFiveCharacters(): List<Hall> {
        var count = 0
        return halls.filter { hall ->
            if (count < 10) {
                if (hall.name.length > 5) {
                    count++
                    true
                }
                false
            }
            else {
                false
            }
        }
    }

    companion object {
        fun generateRandom(faker: Faker? = null, movies: List<MovieBase>? = null, halls: List<Hall>? = null, viewings: List<Viewing>? = null): Theatre {
            val fakerLoc = faker ?: Faker()
            val moviesLoc = movies ?: Movie.generateRandom(fakerLoc, 100)
            moviesLoc.plus(Movie("Star Wars"))
            val hallsLoc = halls ?: Hall.generateRandom(fakerLoc, 15)
            val viewingsLoc = viewings ?: Viewing.generateViewings(100, hallsLoc, moviesLoc)
            return Theatre(hallsLoc, moviesLoc, viewingsLoc)
        }

        fun generateRandom(n: Int, faker: Faker? = null, movies: List<MovieBase>? = null, halls: List<Hall>? = null, viewings: List<Viewing>? = null): List<Theatre> {
            val fakerLoc = faker ?: Faker()
            return List(n) {
                generateRandom(fakerLoc, movies, halls, viewings)
            }
        }
    }
}