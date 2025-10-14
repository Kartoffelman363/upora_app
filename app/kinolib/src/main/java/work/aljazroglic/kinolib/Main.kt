package work.aljazroglic.kinolib

import io.github.serpro69.kfaker.Faker

fun main() {
    val faker = Faker()
    val movies = List(20) { i ->
        Movie(faker.movie.unique.title())
    }
    val halls = List(5) {
        Hall((60..120).random(), faker.space.unique.planet())
    }
    println("Halls:\n\t${halls.joinToString("\n\t")}\n")
    println("Halls sorted by size:\n\t${halls.sorted().joinToString("\n\t")}\n")
    val viewings = Viewing.generateViewings(100, halls, movies)
    val theatre = Theatre(halls, viewings)
    println("$theatre\n")

    try {
        viewings.first().filledCapacity = -1
    } catch (e: IllegalArgumentException) {
        println(e)
    }
    try {
        viewings.first().filledCapacity = 3000
    } catch (e: IllegalArgumentException) {
        println(e)
    }
    try {
        println(viewings[4000].toString())
    } catch (e: IndexOutOfBoundsException) {
        println(e)
    }
}