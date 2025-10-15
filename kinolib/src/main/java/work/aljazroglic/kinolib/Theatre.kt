package work.aljazroglic.kinolib

import io.github.serpro69.kfaker.Faker

class Theatre (val halls: List<Hall> = emptyList(), val movies: List<Movie>, val viewings: List<Viewing>) {
    override fun toString(): String {
        return "Viewings:\n\t${viewings.joinToString("\n\t")}"
    }

    fun findMoviesByName(name: String): List<Movie>? {
        return this.movies.filter { movie -> movie.name.contains(name) } as List<Movie>?
    }

    fun findMoviesByNameCount(name: String): Int {
        return findMoviesByName(name)?.size ?: 0
    }

    fun filterMovies(name: String): List<Movie>? {
        return this.movies.filter { movie -> !movie.name.contains(name) } as List<Movie>?
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
        fun generateRandom(faker: Faker, movies: List<Movie>? = null, halls: List<Hall>? = null, viewings: List<Viewing>? = null): Theatre {
            val moviesLoc = movies ?: Movie.generateRandom(faker, 100)
            moviesLoc.plus(Movie("Star Wars"))
            val hallsLoc = halls ?: Hall.generateRandom(faker, 15)
            val viewingsLoc = viewings ?: Viewing.generateViewings(100, hallsLoc, moviesLoc)
            return Theatre(hallsLoc, moviesLoc, viewingsLoc)
        }

        fun generateRandom(faker: Faker, n: Int, movies: List<Movie>? = null, halls: List<Hall>? = null, viewings: List<Viewing>? = null): List<Theatre> {
            return List(n) {
                generateRandom(faker, movies, halls, viewings)
            }
        }
    }
}