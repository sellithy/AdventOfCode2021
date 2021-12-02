import java.io.File

fun main() {
    println("Part 1: ${part1()}")
    println("Part 2: ${part2()}")
}

private fun part1() =
    File(ClassLoader.getSystemResource("inputDay1").file).useLines { lines ->
        lines.map(String::toInt)
            .zipWithNext { a, b -> b - a }
            .count { it > 0 }
    }

private fun part2() =
    File(ClassLoader.getSystemResource("inputDay1").file).useLines { lines ->
        lines.map(String::toInt)
            .windowed(3) { it.sum() }
            .zipWithNext() { a, b ->  b - a}
            .count { it > 0 }
    }
