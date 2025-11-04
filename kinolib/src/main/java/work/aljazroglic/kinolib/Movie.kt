package work.aljazroglic.kinolib

import io.github.serpro69.kfaker.Faker
import kotlin.random.Random

open class Movie (val name: String) {
    override fun toString(): String {
        return "Movie: $name"
    }

    companion object {
        fun generateRandom(faker: Faker? = null): Movie {
            val fakerLoc = faker ?: Faker()
            return Movie(fakerLoc.movie.unique.title())
        }

        fun generateRandom(faker: Faker? = null, n: Int): List<Movie> {
            val fakerLoc = faker ?: Faker()
            return List(n) {
                return List(100) { i ->
                    if (Random.nextBoolean()) {
                        RatedMovie.generateRandom(fakerLoc)
                    }
                    else {
                        generateRandom(fakerLoc)
                    }
                }
            }
        }
    }
}