@file:OptIn(ExperimentalUuidApi::class)

package work.aljazroglic.kinolib

import io.github.serpro69.kfaker.Faker
import kotlinx.serialization.Serializable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Serializable
class Hall(val capacity: Int, val name: String, val uuid: Uuid = Uuid.random()) : Sizable, Comparable<Hall> {
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
        fun generateRandom(faker: Faker? = null): Hall {
            val fakerLoc = faker ?: Faker()
            return Hall((60..120).random(), fakerLoc.space.unique.moon())
        }

        fun generateRandom(faker: Faker? = null, n: Int): List<Hall> {
            val fakerLoc = faker ?: Faker()
            return List(n) {
                generateRandom(fakerLoc)
            }
        }
    }
}