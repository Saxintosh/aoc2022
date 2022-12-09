package aoc2022.day03

import readLines


fun main() {

	fun part1(list: List<String>): Int {
		return list.map {
			val len = it.length
			val a = it.substring(0, len / 2).toSet()
			val b = it.substring(len / 2).toSet()
			val x = (a intersect b).first()
			if (x.isLowerCase())
				x - 'a' + 1
			else
				x - 'A' + 27
		}.sum()
	}

	fun part2(list: List<String>): Int {
		return list.windowed(3, 3) {
			val (l1, l2, l3) = it
			val x = ((l1.toSet() intersect l2.toSet()) intersect l3.toSet()).first()
			if (x.isLowerCase())
				x - 'a' + 1
			else
				x - 'A' + 27
		}.sum()
	}

	readLines(157, 70, ::part1, ::part2)
}
