package work.aljazroglic.kinolib

import io.github.serpro69.kfaker.Faker
import kotlinx.serialization.Serializable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Serializable
class RatedMovie @OptIn(ExperimentalUuidApi::class) constructor(override var name: String, override val id: Uuid = Uuid.random(), var rating: Int): MovieBase(){
    override fun toString(): String {
        return "Movie: $name rated $rating/10"
    }

    companion object {
        @OptIn(ExperimentalUuidApi::class)
        fun generateRandom(faker: Faker? = null): RatedMovie {
            val fakerLoc = faker ?: Faker()
            return RatedMovie(fakerLoc.movie.unique.title(), Uuid.random(), (1..10).random())
        }

        fun generateRandom(faker: Faker? = null, n: Int): List<RatedMovie> {
            val fakerLoc = faker ?: Faker()
            return List(n) {
                generateRandom(fakerLoc)
            }
        }
    }

    init {
        if (!(1..10).contains(rating)) {
            throw NumberOutOfRangeException("Rating out of range (1, 10)")
        }
    }
}