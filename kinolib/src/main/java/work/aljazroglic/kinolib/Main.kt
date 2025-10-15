package work.aljazroglic.kinolib

import io.github.serpro69.kfaker.Faker

fun main() {
    val faker = Faker()
    val movies = Movie.generateRandom(faker, 100)
    movies.plus("Star Wars")
    val halls = Hall.generateRandom(faker, 15)
    println("Halls:\n\t${halls.joinToString("\n\t")}\n")
    println("Halls sorted by size:\n\t${halls.sorted().joinToString("\n\t")}\n")
    val theatre = Theatre.generateRandom(faker, halls = halls, movies = movies)
    println("$theatre\n")

    println("Average occupation of movie viewings ${theatre.averageOccupationOfViewings()}\n")

    try {
        theatre.viewings.first().filledCapacity = -1
    } catch (e: NumberOutOfRangeException) {
        println(e)
    }
    try {
        theatre.viewings.first().filledCapacity = 3000
    } catch (e: NumberOutOfRangeException) {
        println(e)
    }
    try {
        println(theatre.viewings[4000].toString())
    } catch (e: IndexOutOfBoundsException) {
        println(e)
    }
    try {
        val x = 1 / 0
    } catch (e: ArithmeticException) {
        println(e)
    }
}