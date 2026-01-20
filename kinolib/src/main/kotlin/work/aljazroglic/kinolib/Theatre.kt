@file:OptIn(ExperimentalUuidApi::class)

package work.aljazroglic.kinolib

import io.github.serpro69.kfaker.Faker
import kotlinx.serialization.Serializable
import kotlin.uuid.ExperimentalUuidApi

@Serializable
class Theatre (var movies: MutableList<MovieBase>) {
    fun findMoviesByName(name: String): List<MovieBase>? {
        return this.movies.filter { movie -> movie.name.contains(name) } as List<MovieBase>?
    }

    fun findMoviesByNameCount(name: String): Int {
        return findMoviesByName(name)?.size ?: 0
    }

    fun filterMovies(name: String): List<MovieBase>? {
        return this.movies.filter { movie -> !movie.name.contains(name) } as List<MovieBase>?
    }

    companion object {
        fun generateRandom(faker: Faker? = null, movies: MutableList<MovieBase>? = null): Theatre {
            val fakerLoc = faker ?: Faker()
            val moviesLoc = movies ?: Movie.generateRandom(fakerLoc, 100)
            moviesLoc.plus(Movie("Star Wars"))
            return Theatre(moviesLoc)
        }

        fun generateRandom(n: Int, faker: Faker? = null, movies: MutableList<MovieBase>? = null, halls: List<Hall>? = null, viewings: List<Viewing>? = null): List<Theatre> {
            val fakerLoc = faker ?: Faker()
            return List(n) {
                generateRandom(fakerLoc, movies)
            }
        }
    }
}