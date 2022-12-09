import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

fun readInputAsSequence() = findFile("input.txt").bufferedReader().lineSequence()
fun readInputAsList() = findFile("input.txt").readLines()
fun readInputAsText() = findFile("input.txt").readText()

fun readTestAsSequence() = findFile("test.txt").bufferedReader().lineSequence()
fun readTestAsList() = findFile("test.txt").readLines()
fun readTestAsText() = findFile("test.txt").apply { println(this.absolutePath) }.readText()

fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)

private fun findFile(name: String): File {
    val e = Exception()
    val st = e.stackTrace[3]
    val partial = st.className.split(".").dropLast(1).joinToString("/")
    return File("aoc2022/src/$partial/$name")
}

fun <T> readAll(test1: T, test2: T, part1: (String) -> T, part2: (String) -> T) {
    val testInput = readTestAsText()
    val input = readInputAsText()

    val res1 = part1(testInput)
    println("TestRes1 = $res1")
    check(res1 == test1)
    println("Res1 = ${part1(input)}")

    val res2 = part2(testInput)
    println("TestRes2 = $res2")
    check(res2 == test2)
    println("Res2 = ${part2(input)}")
}

fun <T> readLines(test1: T, test2: T, part1: (List<String>) -> T, part2: (List<String>) -> T) {
    val testInput = readTestAsList()
    val input = readInputAsList()

    val res1 = part1(testInput)
    println("TestRes1 = $res1")
    check(res1 == test1)
    println("Res1 = ${part1(input)}")

    val res2 = part2(testInput)
    println("TestRes2 = $res2")
    check(res2 == test2)
    println("Res2 = ${part2(input)}")
}