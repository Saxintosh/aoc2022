package aoc2022.day05

import readAll

class Cmd(txt: String) {
	val qty: Int
	val c1: Int
	val c2: Int

	init {
		val s = txt.split(" ")
		qty = s[1].toInt()
		c1 = s[3].toInt() - 1
		c2 = s[5].toInt() - 1
	}

	companion object {
		fun fromTxt(txt: String) = txt.lines().map { Cmd(it) }
	}
}

class Stacks(txt: String) {
	private val stacks: Array<MutableList<Char>>

	init {
		val l = txt.lines()
		val last = l.last()
		val count = last.trim().split(" ").last().toInt()

		stacks = Array(count) { mutableListOf() }
		l.dropLast(1).reversed().forEach { line ->
			(0 until count).forEach { index ->
				val ch = line[index * 4 + 1]
				if (ch != ' ')
					stacks[index].add(ch)
			}
		}
	}

	fun execute1(cmd: Cmd) {
		repeat(cmd.qty) {
			val last = stacks[cmd.c1].removeLast()
			stacks[cmd.c2].add(last)
		}
	}

	fun execute2(cmd: Cmd) {
		val x = stacks[cmd.c1].takeLast(cmd.qty)
		repeat(cmd.qty) { stacks[cmd.c1].removeLast() }
		stacks[cmd.c2].addAll(x)
	}

	fun getTop() = String(stacks.map { it.last() }.toCharArray())
}

fun main() {

	fun decode(txt: String): Pair<Stacks, List<Cmd>> {
		val (a, b) = txt.split("\n\n")
		return Stacks(a) to Cmd.fromTxt(b)
	}


	fun part1(txt: String): String {
		val (stacks, cmdList) = decode(txt)
		cmdList.forEach { stacks.execute1(it) }
		return stacks.getTop()
	}

	fun part2(txt: String): String {
		val (stacks, cmdList) = decode(txt)
		cmdList.forEach { stacks.execute2(it) }
		return stacks.getTop()
	}

	readAll("CMZ", "MCD", ::part1, ::part2)
}
