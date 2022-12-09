package aoc2022.day09

import readLines
import kotlin.math.abs

data class P(val x: Int, val y: Int)

class Board(private val len: Int) {
	private var rope = Array(len) { P(0, 0) }
	private var head
		get() = rope[0]
		set(p) {
			rope[0] = p
		}
	private val tail get() = rope.last()

	val set = HashSet<P>().apply { add(tail) }

	private fun up() {
		head = P(head.x, head.y + 1)
		moveTails()
	}

	private fun down() {
		head = P(head.x, head.y - 1)
		moveTails()
	}

	private fun left() {
		head = P(head.x - 1, head.y)
		moveTails()
	}

	private fun right() {
		head = P(head.x + 1, head.y)
		moveTails()
	}

	private fun moveTails() {
		for (k in 1 until len) {
			val dx = rope[k - 1].x - rope[k].x
			val dy = rope[k - 1].y - rope[k].y

			if (abs(dx) <= 1 && abs(dy) <= 1)
				continue

			if (abs(dx) == 2 && abs(dy) == 2) {
				rope[k] = P(rope[k].x + dx / 2, rope[k].y + dy / 2)
				continue
			}

			// only one (dx or dy) is == |2|
			when {
				dx == 2  -> rope[k] = P(rope[k - 1].x - 1, rope[k - 1].y)
				dx == -2 -> rope[k] = P(rope[k - 1].x + 1, rope[k - 1].y)
				dy == 2  -> rope[k] = P(rope[k - 1].x, rope[k - 1].y - 1)
				dy == -2 -> rope[k] = P(rope[k - 1].x, rope[k - 1].y + 1)
			}
		}
		set.add(tail)
	}

	fun process(line: String, draw: Boolean = false) {
		val (p1, p2) = line.split(" ")
		val rep = p2.toInt()
		when (p1) {
			"U" -> repeat(rep) { up() }
			"D" -> repeat(rep) { down() }
			"L" -> repeat(rep) { left() }
			"R" -> repeat(rep) { right() }
		}
		if (draw)
			draw()
	}

	@Suppress("MemberVisibilityCanBePrivate")
	fun draw() {
		val fullSet = set.plus(rope)
		val xr = fullSet.minOf { it.x }..fullSet.maxOf { it.x }
		val yr = fullSet.maxOf { it.y } downTo fullSet.minOf { it.y }

		for (y in yr) {
			for (x in xr) {
				val curr = P(x, y)
				val ch = when {
					curr == head     -> "H"
					curr in rope     -> rope.indexOfFirst { it == curr }.toString()
					0 == x && 0 == y -> "s"
					P(x, y) in set   -> "#"
					else             -> "."
				}
				print(ch)
			}
			println()
		}

		println()
	}
}

fun main() {


	fun part1(lines: List<String>): Int {
		val b = Board(2)
		lines.forEach(b::process)
		return b.set.count()
	}

	fun part2(lines: List<String>): Int {
		val b = Board(10)
		lines.forEach(b::process)
		return b.set.count()
	}


	readLines(88, 36, ::part1, ::part2)
}