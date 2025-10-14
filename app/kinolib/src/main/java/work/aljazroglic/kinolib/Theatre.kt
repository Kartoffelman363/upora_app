package work.aljazroglic.kinolib

class Theatre (val halls: List<Hall> = emptyList(), val viewings : List<Viewing>) {
    override fun toString(): String {
        return "Viewings:\n\t${viewings.joinToString("\n\t")}"
    }
}