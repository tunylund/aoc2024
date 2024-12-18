package day2

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day2Test {

    val input = """
        7 6 4 2 1
        1 2 7 8 9
        9 7 6 2 1
        1 3 2 4 5
        8 6 4 4 1
        1 3 6 7 9
    """.trimIndent()

    val parsedInput = listOf(
        listOf(7, 6, 4, 2, 1),
        listOf(1, 2, 7, 8, 9),
        listOf(9, 7, 6, 2, 1),
        listOf(1, 3, 2, 4, 5),
        listOf(8, 6, 4, 4, 1),
        listOf(1, 3, 6, 7, 9),
    )

    val steps = listOf(
        listOf(0, -1, -2, -2, -1),
        listOf(0, 1, 5, 1, 1),
        listOf(0, -2, -1, -4, -1),
        listOf(0, 2, -1, 2, 1),
        listOf(0, -2, -2, 0, -3),
        listOf(0, 2, 3, 1, 2),
    )

    val dampenedByDirectionOffenders = listOf(
        listOf(7, 6, 4, 2, 1),
        listOf(1, 2, 7, 8, 9),
        listOf(9, 7, 6, 2, 1),
        listOf(1, 2, 4, 5),
        listOf(8, 6, 4, 4, 1),
        listOf(1, 3, 6, 7, 9),
    )

    val dampenedByStrideOffenders = listOf(
        listOf(7, 6, 4, 2, 1),
        listOf(1, 2, 8, 9),
        listOf(9, 7, 6, 1),
        listOf(1, 3, 2, 4, 5),
        listOf(8, 6, 4, 1),
        listOf(1, 3, 6, 7, 9),
    )

    val dampened = listOf(
        listOf(7, 6, 4, 2, 1),
        listOf(1, 2, 8, 9),
        listOf(9, 7, 6, 1),
        listOf(1, 2, 4, 5),
        listOf(8, 6, 4, 1),
        listOf(1, 3, 6, 7, 9),
    )

    val safetyCheck = listOf(
        true,
        false,
        false,
        false,
        false,
        true
    )

    @Test
    fun shouldParseInput() {
        assertEquals(parsedInput, parseInput(input))
    }

    @Test
    fun shouldCalculateSteps() {
        assertEquals(steps, parsedInput.map(::calculateSteps))
    }

    @Test
    fun shouldDampenByDirection() {
        assertEquals(dampenedByDirectionOffenders, parsedInput.map(::dampenDirectionOffenders))
    }

    @Test
    fun shouldDampenDirectionOfLastElement() {
        assertEquals(listOf(1, 2, 3, 4), dampenDirectionOffenders(listOf(1, 2, 3, 4, 3)))
    }

    @Test
    fun shouldDampenOffendingStrides() {
        assertEquals(dampenedByStrideOffenders, parsedInput.map(::dampenStrideOffenders))
    }

    @Test
    fun shouldDampenOffendingFirstStep() {
        assertEquals(listOf(6, 7, 8, 9), dampenStrideOffenders(listOf(1, 6, 7, 8, 9)))
    }

    @Test
    fun shouldDampenOffendingSteps() {
        assertEquals(dampened, dampenOffendingSteps(parsedInput))
    }

    @Test
    fun shouldEvaluateSafety() {
        assertEquals(safetyCheck, parsedInput.map(::evaluateSafety))
    }

    @Test
    fun shouldEvaluateSafetyOfDampenedSteps() {
        assertEquals(listOf(
            true,
            false,
            false,
            true,
            true,
            true
        ), dampened.map(::evaluateSafety))
    }
}