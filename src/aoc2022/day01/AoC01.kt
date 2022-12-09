package aoc2022.day01

import readAll

fun main() {

	fun buildList(txt: String): List<Int> = txt
		.split("\n\n")
		.map {
			it.lines()
				.sumOf { line ->
					line.toInt()
				}
		}

	fun part1(txt: String): Int = buildList(txt).max()

	fun part2(txt: String): Int = buildList(txt).sortedDescending().take(3).sum()

	readAll(24000, 45000, ::part1, ::part2)
}