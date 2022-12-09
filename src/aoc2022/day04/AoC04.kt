package aoc2022.day04

import readLines


fun main() {

	fun String.toIntRange() = split("-").map { it.toInt() }.let { it[0]..it[1] }
	fun decode(line: String) = line.split(",").let { it[0].toIntRange() to it[1].toIntRange() }

	operator fun IntRange.contains(other: IntRange) = first <= other.first && last >= other.last
	infix fun IntRange.overlaps(other: IntRange) = last in other


	fun part1(list: List<String>) = list.count {
		val (r1, r2) = decode(it)
		r1 in r2 || r2 in r1
	}

	fun part2(list: List<String>) = list.count {
		val (r1, r2) = decode(it)
		r1 overlaps r2 || r2 overlaps r1
	}

	readLines(2, 4, ::part1, ::part2)
}
