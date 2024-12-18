package day5

fun day5a(input: String): Int {
    val parsedInput = parseInput(input)
    val rules = parsedInput.first
    val updates = parsedInput.second
    return updates
        .filter { it.isValid(rules) }
        .map { it.middlePage }
        .reduce { acc, i -> acc + i }
}

fun day5b(input: String): Int {
    val parsedInput = parseInput(input)
    val rules = parsedInput.first
    val updates = parsedInput.second
    updates
        .filter { !it.isValid(rules) }
        .forEach {
            val o = it.reorder(rules)
            if (!o.isValid(rules)) {
                println("o:" + it.pages + o.pages)
            }
        }
    return updates
        .filter { !it.isValid(rules) }
        .map { it.reorder(rules) }
        .map { it.middlePage }
        .reduce { acc, i -> acc + i }
}

fun parseInput(input: String): Pair<List<Rule>, List<Update>> {
    val rules = mutableListOf<Rule>()
    val updates = mutableListOf<Update>()
    val rulex = Regex("""(?<before>\d+)\|(?<after>\d+)""")
    val updatex = Regex("""\d+""")
    input.split("\n").forEach { line ->
        if (rulex.matches(line)) {
            rulex.findAll(line).forEach { match ->
                val (before, after) = match.destructured
                rules.add(Rule(before.toInt(), after.toInt()))
            }
        } else {
            if (updatex.containsMatchIn(line)) {
                updates.add(Update(updatex.findAll(line).toList().map {
                    it.value.toInt()
                }))
            }
        }
    }
    return Pair(rules, updates)
}

fun findNextLink(cur: List<Int>, goal: Int, rules: List<Rule>): List<Int> {
    val nextRules = rules.filter { it.before == cur.last() }
    nextRules.map { rule ->
        if (rule.after == goal) {
            return cur + listOf(rule.after)
        }
        else {
            val ch = findNextLink(cur + listOf(rule.after), goal, rules)
            if (ch.last() == goal) return ch
        }
    }
    return cur
}

fun findChain(a: Int, b: Int, rules: List<Rule>): List<Int> {
    val ch = findNextLink(listOf(a), b, rules)
    return ch
}

data class Rule(val before: Int, val after: Int) {
    fun applies(pages: List<Int>): Boolean {
        return pages.contains(before) && pages.contains(after)
    }
    fun isValid(pages: List<Int>): Boolean {
        return pages.indexOf(before) < pages.indexOf(after)
    }
}

data class Update(val pages: List<Int>) {
    fun isValid(rules: List<Rule>): Boolean {
        val rulesBroken = rules
            .filter { rule -> rule.applies(pages) }
            .filter { rule -> !rule.isValid(pages) }

        return rulesBroken.isEmpty()
    }

    fun reorder(rules: List<Rule>): Update {
        val sorted = pages.sortedWith { a, b ->
            val aIsBeforeB = rules.find { rule -> rule.before == a && rule.after == b }
            if (aIsBeforeB != null) 1
            else {
                val bIsBeforeA = rules.find { rule -> rule.before == b && rule.after == a }
                if (bIsBeforeA != null) -1
                else {
                    val chainFromAtoB = findChain(a, b, rules)
                    if (chainFromAtoB.last() == b) 1
                    else {
                        val chainFromBtoA = findChain(b, a, rules)
                        if (chainFromBtoA.last() == a) -1
                        else 0
                    }
                }
            }
        }
        return Update(sorted)
    }

    val middlePage = pages.get(pages.size/2)
}