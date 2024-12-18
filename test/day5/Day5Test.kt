package day5

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class Day5Test {

    val parsedInput = parseInput(
        """
            47|53
            97|13
            97|61
            97|47
            75|29
            61|13
            75|53
            29|13
            97|29
            53|29
            61|53
            97|53
            61|29
            47|13
            75|47
            97|75
            47|61
            75|61
            47|29
            75|13
            53|13

            75,47,61,53,29
            97,61,53,29,13
            75,29,13
            75,97,47,61,53
            61,13,29
            97,13,75,29,47
        """.trimIndent()
    )

    @Test
    fun shouldParseInput() {
        assertEquals(Rule(47, 53), parsedInput.first[0])
        assertEquals(Update(listOf(75, 47, 61, 53, 29)), parsedInput.second[0])
    }

    @Test
    fun shouldEvaluateUpdateValidity1() {
        val update = Update(listOf(75,47,61,53,29))
        assertEquals(true, update.isValid(parsedInput.first))
    }

    @Test
    fun shouldEvaluateUpdateValidity2() {
        val update = Update(listOf(97,61,53,29,13))
        assertEquals(true, update.isValid(parsedInput.first))
    }

    @Test
    fun shouldEvaluateUpdateValidity3() {
        val update = Update(listOf(75,29,13))
        assertEquals(true, update.isValid(parsedInput.first))
    }

    @Test
    fun shouldEvaluateUpdateValidity4() {
        val update = Update(listOf(75,97,47,61,53))
        assertEquals(false, update.isValid(parsedInput.first))
    }

    @Test
    fun shouldEvaluateUpdateValidity5() {
        val update = Update(listOf(61,13,29))
        assertEquals(false, update.isValid(parsedInput.first))
    }

    @Test
    fun shouldEvaluateUpdateValidity6() {
        val update = Update(listOf(97,13,75,29,47))
        assertEquals(false, update.isValid(parsedInput.first))
    }

    @Test
    fun shouldFindMiddlePage() {
        val update = Update(listOf(97,13,75,29,47))
        assertEquals(75, update.middlePage)
    }

    @Test
    fun shouldReorderUntilValid1() {
        val update = Update(listOf(75,97,47,61,53))
        assertEquals(listOf(97,75,47,61,53), update.reorder(parsedInput.first).pages)
    }

    @Test
    fun shouldReorderUntilValid2() {
        val update = Update(listOf(61,13,29))
        assertEquals(listOf(61,29,13), update.reorder(parsedInput.first).pages)
    }

    @Test
    fun shouldReorderUntilValid3() {
        val update = Update(listOf(97,13,75,29,47))
        assertEquals(listOf(97,75,47,29,13), update.reorder(parsedInput.first).pages)
    }

    @Test
    fun shouldFindChain() {
        assertEquals(listOf(75, 29), findChain(75, 29, parsedInput.first))
        assertEquals(listOf(97,61,53), findChain(97, 53, parsedInput.first))
        assertEquals(listOf(97,75,47), findChain(97, 47, parsedInput.first))
    }
}