@file:OptIn(ExperimentalUuidApi::class)

package work.aljazroglic.kinolib

import io.github.serpro69.kfaker.Faker
import kotlinx.serialization.Serializable
import kotlin.random.Random
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Serializable
open class Movie (override var name: String, override val id: Uuid = Uuid.random()): MovieBase() {
    override fun toString(): String {
        return "Movie: $name"
    }

    companion object {
        fun generateRandom(faker: Faker? = null): Movie {
            val fakerLoc = faker ?: Faker()
            return Movie(fakerLoc.movie.unique.title())
        }

        fun generateRandom(faker: Faker? = null, n: Int): MutableList<MovieBase> {
            val fakerLoc = faker ?: Faker()
            return MutableList(n) {
                return MutableList(100) { i ->
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