package day4

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day3Test {
    @Test
    fun shouldParseInput() {
        assertEquals(10, parseInput("""
            MMMSXXMASM
            MSAMXMSMSA
            AMXSXMAAMM
            MSAMASMSMX
            XMASAMXAMM
            XXAMMXXAMA
            SMSMSASXSS
            SAXAMASAAA
            MAMMMXMMMM
            MXMXAXMASX
        """.trimIndent()).rows.size)
    }

    @Test
    fun shouldScanForXmases() {
        assertEquals(listOf(
            Pos(0, 0),
            Pos(6, 0),
            Pos(0, 1),
            Pos(6, 1)), scanFor("x", CharMap(arrayOf("xmasasx", "xmasasx"))))
    }

    @Test
    fun shouldValidateXmasXses1() {
        assertEquals(listOf(Pos(0, 0, 1)), scanForXMasxses(CharMap(arrayOf("xmas"))))
        assertEquals(listOf(Pos(1, 0, 0)), scanForXMasxses(CharMap(arrayOf("mxas"))))
    }

    @Test
    fun shouldGetSubstr() {
        // "→↘↓↙←↖↑↗"
        val map = CharMap(arrayOf("xmasasx", "xmasasx"))
        assertEquals("xmas", map.substr(0, 0, '→'))
        assertEquals("sx", map.substr(5, 0, '→'))
    }

    @Test
    fun shouldCalculateAll() {
        val map = parseInput("""
        MMMSXXMASM
        MSAMXMSMSA
        AMXSXMAAMM
        MSAMASMSMX
        XMASAMXAMM
        XXAMMXXAMA
        SMSMSASXSS
        SAXAMASAAA
        MAMMMXMMMM
        MXMXAXMASX
        """.trimIndent())
        val poss = sumValues(scanForXMasxses(map))
        assertEquals(18, poss)
    }

    @Test
    fun shouldFindMasses() {
        val m = parseInput("""
            M.S
            .A.
            M.S
        """.trimIndent())
        assertEquals(1, findMasses(m))
    }

    @Test
    fun shouldFindAllMasses() {
        val m = parseInput("""
            .M.S......
            ..A..MSMS.
            .M.S.MAA..
            ..A.ASMSM.
            .M.S.M....
            ..........
            S.S.S.S.S.
            .A.A.A.A..
            M.M.M.M.M.
            ..........
        """.trimIndent())
        assertEquals(9, findMasses(m))
    }
}