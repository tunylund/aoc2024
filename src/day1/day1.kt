package day1

import kotlin.math.absoluteValue

fun day1a(input: String): Long {
    return calculateTotalDistance(calculateDistances(sort(parseInput(input))))
}

fun day1b(input: String): Long {
    return calculateTotalDistance(calculateSimilarityScores(countTimesNumberAppearsOnRight(parseInput(input))))
}

fun parseInput(input: String): List<Pair<Long, Long>> {
    val rx = Regex("""(?<l>\d+)\s+(?<r>\d+)""")
    val list = mutableListOf<Pair<Long, Long>>()
    input.split("\n").forEach { line ->
        if (rx.containsMatchIn(line)) {
            val (l, r) = rx.find(line)!!.destructured
            list.add(Pair(l.toLong(), r.toLong()))
        }
    }
    return list
}

fun countTimesNumberAppearsOnRight(input: List<Pair<Long, Long>>): List<Pair<Long, Long>> {
    val r = input.map { pair -> pair.second }.sorted()
    return input.mapIndexed { index, (first) ->
        Pair(first, r.count { it == first }.toLong())
    }
}

fun sort(input: List<Pair<Long, Long>>): List<Pair<Long, Long>> {
    val l = input.map { pair -> pair.first }.sorted()
    val r = input.map { pair -> pair.second }.sorted()
    return l.mapIndexed { index, leftValue ->
        Pair(leftValue, r[index])
    }
}

fun calculateDistances(input: List<Pair<Long, Long>>): List<Long> {
    return input.map { pair -> (pair.first - pair.second).absoluteValue }
}

fun calculateSimilarityScores(input: List<Pair<Long, Long>>): List<Long> {
    return input.map { pair -> (pair.first * pair.second) }
}

fun calculateTotalDistance(input: List<Long>): Long {
    return input.reduce { acc, i -> acc + i }
}
