package day3

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day3Test {
    @Test
    fun shouldParseInput() {
        assertEquals(
            listOf(
                Pair(2, 4),
                Pair(5, 5),
                Pair(11, 8),
                Pair(8, 5)
            ), parseInput("xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))")
        )
    }

    @Test
    fun shouldParseInputWithDos() {
        assertEquals(listOf(
            Pair(2, 4),
            Pair(8, 5),
        ), parseComplexInput("xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"))
    }

    @Test
    fun shouldRunInstructiionis() {
        assertEquals(161, operate(listOf(
            Pair(2, 4),
            Pair(5, 5),
            Pair(11, 8),
            Pair(8, 5)
        )))
    }
}