package aoc2022.day06

import readAll


fun main() {

//	fun String.startOfMessage(mLen: Int): Int {
//		val map = HashMap<Char, Int>()
//		var recentIndex = 0
//		var index = 0
//		return indexOfFirst {char ->
//			val lastSeenIndex = map.put(char, index) ?: 0
//			recentIndex = recentIndex.coerceAtLeast(lastSeenIndex)
//			index++ - recentIndex >= mLen
//		} + 1
//	}

	fun part1(txt: String) = txt.windowedSequence(4).indexOfFirst { it.toSet().size == 4 } + 4
	fun part2(txt: String) = txt.windowedSequence(14).indexOfFirst { it.toSet().size == 14 } + 14


	readAll(7, 19, ::part1, ::part2)
}
