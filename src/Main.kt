import day3.day3b
import day4.day4a
import day4.day4b
import day5.day5a
import day5.day5b
import java.io.File

fun main() {
    val f = File("./src/day5/input5.txt")
    val input = f.readText()
    println(day5b(input))
}
