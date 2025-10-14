package work.aljazroglic.kinolib

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
}