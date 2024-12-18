package day1

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day1Test {

    val input = """
        3   4
        4   3
        2   5
        1   3
        3   9
        3   3
        """.trimIndent()

    val parsedInput = listOf(Pair(3L, 4L),
                            Pair(4L, 3L),
                            Pair(2L, 5L),
                            Pair(1L, 3L),
                            Pair(3L, 9L),
                            Pair(3L, 3L))

    val sortedInput = listOf(Pair(1L, 3L),
                            Pair(2L, 3L),
                            Pair(3L, 3L),
                            Pair(3L, 4L),
                            Pair(3L, 5L),
                            Pair(4L, 9L))

    val distances = listOf(2L, 1L, 0L, 1L, 2L, 5L)

    val total = 11L

    val timesAppearOnRight = listOf(Pair(3L, 3L),
                                    Pair(4L, 1L),
                                    Pair(2L, 0L),
                                    Pair(1L, 0L),
                                    Pair(3L, 3L),
                                    Pair(3L, 3L))

    val similarityScores = listOf(9L, 4L, 0L, 0L, 9L, 9L)

    val similarityScore = 31L

    @Test
    fun shouldParseInput() {
        val result = parseInput(input)
        assertEquals(parsedInput, result)
    }

    @Test
    fun shouldSortInput() {
        val result = sort(parsedInput)
        assertEquals(sortedInput, result)
    }

    @Test
    fun shouldCalculateDistances() {
        val result = calculateDistances(sortedInput)
        assertEquals(distances, result)
    }

    @Test
    fun shouldCalculateTotalDistance() {
        val result = calculateTotalDistance(distances)
        assertEquals(total, result)
    }

    @Test
    fun shouldFindtimesAppearOnRight() {
        val result = countTimesNumberAppearsOnRight(parsedInput)
        assertEquals(timesAppearOnRight, result)
    }

    @Test
    fun shouldCalculateSimilarityScores() {
        val result = calculateSimilarityScores(timesAppearOnRight)
        assertEquals(similarityScores, result)
    }

    @Test
    fun shouldCalculateSimilarityScore() {
        val result = calculateTotalDistance(similarityScores)
        assertEquals(similarityScore, result)
    }
}