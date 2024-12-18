package day6

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class Day6Test {

    val parsedInput = parseInput(
        """
        ....#.....
        .........#
        ..........
        ..#.......
        .......#..
        ..........
        .#..^.....
        ........#.
        #.........
        ......#...
        """.trimIndent()
    )

    @Test
    fun shouldParseInput() {
        assertEquals(Pos(10, 10), parsedInput.size)
        assertEquals(8, parsedInput.pillars.size)
        assertEquals(Pos(4, 0), parsedInput.pillars[0])
        assertEquals(Pos(4, 6), parsedInput.guards[0].pos)
        assertEquals(Pos(0, -1), parsedInput.guards[0].dir)
    }

    @Test
    fun shouldSimulate() {
        val g = step(parsedInput)
        assertEquals(g.guards[0], Pos(4, 5))
    }

    @Test
    fun shouldRotate() {
        assertEquals(Pos(1, 0), Pos(0, -1).rotate())
        assertEquals(Pos(0, 1), Pos(1, 0).rotate())
        assertEquals(Pos(-1, 0), Pos(0, 1).rotate())
        assertEquals(Pos(0, -1), Pos(-1, 0).rotate())
    }

}
