package work.aljazroglic.kinolib

import io.github.serpro69.kfaker.Faker

class RatedMovie(name: String, val rating: Int): Movie(name){
    override fun toString(): String {
        return "${super.toString()} rated $rating/10"
    }

    companion object {
        fun generateRandom(faker: Faker? = null): RatedMovie {
            val fakerLoc = faker ?: Faker()
            return RatedMovie(fakerLoc.movie.unique.title(), (1..10).random())
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