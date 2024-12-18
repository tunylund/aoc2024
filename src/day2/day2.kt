package day2

import kotlin.math.absoluteValue
import kotlin.math.sign

fun day2a(input: String): Int {
    return numSafeRecords(parseInput(input).map(::evaluateSafety))
}

fun day2b(input: String): Int {
    val parsedInput = parseInput(input)
    val evaluated = parsedInput.map { record ->
        if (evaluateSafety(record)) {
            true
        } else {
            if (evaluateSafety(dampenDirectionOffenders(record))) true
            else {
                if (evaluateSafety(dampenStrideOffenders(record))) true
                else {
                    println("wtf" + record)
                    false
                }
            }
        }
    }
    return numSafeRecords(evaluated)
}

fun parseInput(input: String): List<List<Int>> {
    return input.split("\n").map { line ->
        line.split(Regex("\\s+")).map { c -> c.toInt() }
    }
}

fun calculateSteps(record: List<Int>): List<Int> {
    return record.mapIndexed { index: Int, level: Int ->
        val previousLevel = record.getOrElse(index - 1, { level })
        level - previousLevel
    }
}

fun dampenDirectionOffenders(record: List<Int>): List<Int> {
    val steps = calculateSteps(record)
    val asc = steps.count { it.sign > 0 }
    val desc = steps.count { it.sign < 0 }
    if (asc > 0 && desc > 0) {
        val offendingDir = if (asc > desc) -1 else 1
        val ixOfFirstOffendingStepL = steps.indexOfFirst { it.sign == offendingDir } - 1
        val ixOfFirstOffendingStepR = ixOfFirstOffendingStepL + 1
        if (ixOfFirstOffendingStepL > -1) {
            val recordWithoutL = record.subList(0, ixOfFirstOffendingStepL) + record.subList(ixOfFirstOffendingStepL + 1, record.size)
            val recordWithoutR = record.subList(0, ixOfFirstOffendingStepR) + record.subList(ixOfFirstOffendingStepR + 1, record.size)
            if (evaluateSafety(recordWithoutL)) return recordWithoutL
            if (evaluateSafety(recordWithoutR)) return recordWithoutR
        }
    }

    return record
}

fun dampenStrideOffenders(record: List<Int>): List<Int> {
    val steps = calculateSteps(record)
    val ixOfFirstOffendingStep = steps.drop(1).indexOfFirst { it.absoluteValue < 1 }
    if (ixOfFirstOffendingStep > -1) {
        return record.minusElement(record[ixOfFirstOffendingStep + 1])
    } else {
        val ixOfFirstOffendingStepL = steps.indexOfFirst { it.absoluteValue > 3 } - 1
        val ixOfFirstOffendingStepR = ixOfFirstOffendingStepL + 1
        if (ixOfFirstOffendingStepL > -1) {
            val recordWithoutL = record.minusElement(record[ixOfFirstOffendingStepL])
            val recordWithoutR = record.minusElement(record[ixOfFirstOffendingStepR])
            if (evaluateSafety(recordWithoutL)) return recordWithoutL
            else if (evaluateSafety(recordWithoutR)) return recordWithoutR
        }
    }

    return record
}

fun dampenOffendingSteps(input: List<List<Int>>): List<List<Int>> {
    return input.map { record ->
        val directionDampened = dampenDirectionOffenders(record)
        val strideDampened = dampenStrideOffenders(record)
        if (directionDampened.size < record.size) directionDampened else strideDampened
    }
}

fun evaluateSafety(record: List<Int>): Boolean {
    val steps = calculateSteps(record).drop(1)
    val direction = steps.first().sign
    val allHeadToSameDirection = steps.all { it.sign == direction }
    val allDifferAtLeastOneAtMostThree = steps.all { it.absoluteValue in 1 .. 3 }
    return allHeadToSameDirection && allDifferAtLeastOneAtMostThree
}

fun numSafeRecords(input: List<Boolean>): Int {
    return input.count { it }
}