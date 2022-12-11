package aoc2022.day11

import readLines

var isDivisor = true

var allDivisor = 1L

class Monkey(
	private val items: MutableList<Long>,
	val op: (Long) -> Long,
	val divisor: Long,
	val test: (Long) -> Int,
) {
	var inspection = 0L
	fun round(monkeys: List<Monkey>) {
		items.forEach { item ->
			inspection++
			var wl = op(item)
			if (isDivisor)
				wl /= 3
			wl %= allDivisor
			val target = test(wl % divisor)
			monkeys[target].items.add(wl)
		}
		items.clear()
	}
}

fun parse(list: List<String>): List<Monkey> {
	val l = list.windowed(7, 7, true).map { rows ->
		val rWords = rows.map { it.trim().split(" ") }
		val items = rWords[1].drop(2).map { it.removeSuffix(",").toLong() }.toMutableList()
		val opConst = rWords[2][5].toLongOrNull()
		val op: (Long) -> Long = when (rWords[2][4]) {
			"+"  -> {
				{ it + (opConst ?: it) }
			}

			"*"  -> {
				{ it * (opConst ?: it) }
			}

			else -> throw Exception("Unexpected")
		}
		val divisor = rWords[3][3].toLong()
		val m1 = rWords[4].last().toInt()
		val m2 = rWords[5].last().toInt()
		val test: (Long) -> Int = { if (it == 0L) m1 else m2 }
		Monkey(items, op, divisor, test)
	}

	allDivisor = l.fold(1L) { d, m -> d * m.divisor }

	return l
}

fun main() {

	fun part1(lines: List<String>): Long {
		val monkeys = parse(lines)
		repeat(20) {
			monkeys.forEach { it.round(monkeys) }
		}
		val (m1, m2) = monkeys.sortedBy { it.inspection }.takeLast(2)
		return m1.inspection * m2.inspection
	}

	fun part2(lines: List<String>): Long {
		isDivisor = false
		val monkeys = parse(lines)
		repeat(10000) {
			monkeys.forEach { it.round(monkeys) }
		}
		val (m1, m2) = monkeys.sortedBy { it.inspection }.takeLast(2)
		return m1.inspection * m2.inspection
	}


	readLines(10605L, 2713310158L, ::part1, ::part2)
}