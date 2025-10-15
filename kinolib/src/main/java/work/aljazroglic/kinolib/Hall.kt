package work.aljazroglic.kinolib

import io.github.serpro69.kfaker.Faker

class Hall(val capacity: Int, val name: String) : Sizable, Comparable<Hall> {
    override fun toString(): String {
        return "Hall: $name; Capacity: $capacity"
    }

    override fun size(): Int {
        return capacity
    }

    override fun compareTo(other: Hall): Int {
        return this.size() - other.size()
    }

    companion object {
        fun generateRandom(faker: Faker): Hall {
            return Hall((60..120).random(), faker.space.unique.moon())
        }

        fun generateRandom(faker: Faker, n: Int): List<Hall> {
            return List(n) {
                generateRandom(faker)
            }
        }
    }
}