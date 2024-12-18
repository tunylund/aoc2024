package day3

fun day3a(input: String): Int {
    return operate(parseInput(input))
}

fun day3b(input: String): Int {
    return operate(parseComplexInput(input))
}

fun parseInput(input: String): List<Pair<Int, Int>> {
    val rx = Regex(  """mul\((?<a>\d+),(?<b>\d+)\)""")
    val matchers = rx.findAll(input)
    return matchers.toList().map {
        val (a, b) = it.destructured
        Pair(a.toInt(), b.toInt())
    }
}

fun parseComplexInput(input: String): List<Pair<Int, Int>> {
    val doBlocks = ("do()" + input).split("do()").map { it.split("don't()")[0] }
    val instructions = doBlocks.map(::parseInput)
    return instructions.reduce { acc, list -> acc + list }
}

fun operate(input: List<Pair<Int, Int>>): Int {
    return input.map { pair: Pair<Int, Int> ->
        pair.first * pair.second
    }.reduce { acc, value -> acc + value}
}
