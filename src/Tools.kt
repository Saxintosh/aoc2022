fun myRange(a: Int, b: Int) = when (a < b) {
	true -> a..b
	else -> b..a
}