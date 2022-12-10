package aoc2022.day10

import readLines

class Cpu(private val prg: List<String>, val peekBlock: Cpu.() -> Unit) {
	var x = 1
	var cycle = 1

	private var longAdd: Int? = null
	private var pc = 0

	private fun doCycle() {
		peekBlock(this)
		cycle++

		val somethingToAdd = longAdd
		if (somethingToAdd != null) {
			x += somethingToAdd
			longAdd = null
			return
		}

		val s = prg[pc++].split(" ")
		if (s[0] == "addx")
			longAdd = s[1].toInt()
	}

	private fun reset() {
		x = 1
		cycle = 1
		longAdd = null
	}

	fun run() {
		reset()
		while (pc < prg.size)
			doCycle()
	}
}

class Crt {
	private val pixels = Array(6) { CharArray(40) { ' ' } }

	private fun toRowCol(cycle: Int): Pair<Int, Int> {
		val pos = (cycle - 1) % 240
		val row = pos / 40
		val col = pos % 40
		return row to col
	}

	fun printScreen() {
		pixels.forEach {
			println(String(it))
		}
		println()
	}

	fun draw(cycle: Int, x: Int) {
		val xx = x % 40
		val xr = xx - 1..xx + 1
		val (row, col) = toRowCol(cycle)
		if (col in xr)
			pixels[row][col] = '#'
		else
			pixels[row][col] = '.'
	}
}


fun main() {

	fun part1(lines: List<String>): Int {
		var signalStrength = 0
		Cpu(lines) {
			if ((cycle + 20) % 40 == 0)
				signalStrength += cycle * x
		}.run()

		return signalStrength
	}

	fun part2(lines: List<String>): Int {
		val crt = Crt()
		Cpu(lines) {
			crt.draw(cycle, x)
		}.run()
		crt.printScreen()
		return 1
	}


	readLines(13140, 1, ::part1, ::part2)
}