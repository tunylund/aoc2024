package day6

fun parseInput(input: String): GameMap {
    val data = input.split("\n").map { it.toCharArray() }.toTypedArray()
    val size = Pos(data[0].size, data.size)
    val pillars = mutableListOf<Pos>()
    val guards = mutableListOf<Guard>()
    data.mapIndexed { yx, row ->
        row.mapIndexed { xx, char ->
            if (char == '#') pillars.add(Pos(xx, yx))
            if (char == '^') guards.add(Guard(Pos(xx, yx), Pos(0, -1)))
            if (char == '>') guards.add(Guard(Pos(xx, yx), Pos(1, 0)))
            if (char == 'v') guards.add(Guard(Pos(xx, yx), Pos(0, 1)))
            if (char == '<') guards.add(Guard(Pos(xx, yx), Pos(-1, 0)))
        }
    }

    return GameMap(size, pillars, guards)
}

fun step(game: GameMap): GameMap {
    val guards = game.guards.map { guard ->
        if (guard.canMove(game.pillars)) guard.move()
        else guard.turn()
    }.filter { it.isInGame(game.size) }
    return GameMap(game.size, game.pillars, guards)
}

data class Pos(val x: Int, val y: Int) {
    operator fun plus(pos: Pos): Pos {
        return Pos(x + pos.x, y + pos.y)
    }
    operator fun times(pos: Pos): Pos {
        return Pos(x * pos.x, y * pos.y)
    }
    fun rotate(): Pos {
        return Pos(y, x) * Pos(-1, -1)
    }
}
data class Guard(val pos: Pos, val dir: Pos) {
    fun canMove(pillars: List<Pos>): Boolean {
        val nextPos = pos + dir
        return !pillars.contains(nextPos)
    }
    fun move(): Guard {
        return Guard(pos + dir, dir)
    }
    fun turn(): Guard {
        return Guard(pos, dir.rotate())
    }
    fun isInGame(size: Pos): Boolean {
        return pos.x >= 0 && pos.x <= size.x && pos.y >= 0 && pos.y <= size.y
    }
}
data class GameMap(val size: Pos, val pillars: List<Pos>, val guards: List<Guard>)
