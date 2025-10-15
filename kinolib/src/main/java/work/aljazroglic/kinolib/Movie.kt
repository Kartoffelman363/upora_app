package work.aljazroglic.kinolib

import io.github.serpro69.kfaker.Faker
import kotlin.random.Random

open class Movie (val name: String) {
    override fun toString(): String {
        return "Movie: $name"
    }

    companion object {
        fun generateRandom(faker: Faker): Movie {
            return Movie(faker.movie.unique.title())
        }

        fun generateRandom(faker: Faker, n: Int): List<Movie> {
            return List(n) {
                return List(100) { i ->
                    if (Random.nextBoolean()) {
                        RatedMovie.generateRandom(faker)
                    }
                    else {
                        generateRandom(faker)
                    }
                }
            }
        }
    }
}