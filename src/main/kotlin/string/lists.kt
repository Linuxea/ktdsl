package string

import java.util.*


fun listof(vararg args: Int): List<Int> {
    return LinkedList<Int>().apply {
        args.forEach { arg -> this.add(arg) }
    }

}

fun main() {
    println(listof(1, 2, 3, 4, 5))
}