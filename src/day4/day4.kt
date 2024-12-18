package day4

fun day4a(input: String): Int {
    return sumValues(scanForXMasxses(parseInput(input)))
}

fun day4b(input: String): Int {
    return findMasses(parseInput(input))
}

fun parseInput(input: String): CharMap {
    return CharMap(input.split("\n").toTypedArray())
}

fun scanFor(x: String, map: CharMap): List<Pos> {
    return map.rows.mapIndexed { yx, row -> row.mapIndexedNotNull { xx, char ->
        if (char.lowercase() == x) Pos(xx, yx)
        else null
    } }.flatten()
}

fun scanForXMasxses(map: CharMap): List<Pos> {
    return scanFor("x", map).map {
        it.value = "→↘↓↙←↖↑↗".toCharArray().map { dir ->
            if (map.substr(it.x, it.y, dir) == "xmas") 1 else 0
        }.reduce {acc, value -> acc + value }
        it
    }
}

fun ismas(str: String): Boolean {
    return str == "mas" || str.reversed() == "mas"
}

fun findMasses(map: CharMap): Int {
    var score = 0
    for (y  in 0 .. map.rows.size - 1) {
        for (x  in 0 .. map.rows[y].length) {
            if (ismas(map.substr(x, y, '↘', 2)) &&
                ismas(map.substr(x, y + 2, '↗', 2))) score ++
        }
    }
    return score
}

fun sumValues(poss: List<Pos>): Int {
    return poss.map { it.value }.reduce { acc, value -> acc + value }
}

data class Pos(val x: Int, val y: Int, var value: Int = -1)

data class CharMap(val rows: Array<String>) {
    fun substr(x: Int, y: Int, dir: Char, len: Int = 3): String {
        val str = StringBuilder()
        if (dir == '→') {
            for(i in 0..len) str.append(at(y, x + i))
        }
        if (dir == '↘') {
            for(i in 0..len) str.append(at(y + i, x + i))
        }
        if (dir == '↓') {
            for(i in 0..len) str.append(at(y + i, x))
        }
        if (dir == '↙') {
            for(i in 0..len) str.append(at(y + i, x - i))
        }
        if (dir == '←') {
            for(i in 0..len) str.append(at(y, x - i))
        }
        if (dir == '↖') {
            for(i in 0..len) str.append(at(y - i, x - i))
        }
        if (dir == '↑') {
            for(i in 0..len) str.append(at(y - i, x))
        }
        if (dir == '↗') {
            for(i in 0..len) str.append(at(y - i, x + i))
        }

        return str.toString().lowercase()
    }

    private fun at(y: Int, x: Int): String {
        if (y >= 0 && y < rows.size) {
            if (x >= 0 && x < rows[y].length) {
                return rows[y][x].toString()
            }
        }
        return ""
    }
}