import java.io.File

fun main() {
    println("Part 1: ${part1()}")
    println("Part 2: ${part2()}")
}

enum class Command {
    Forward, Down, Up
}

private fun part1() =
    File(ClassLoader.getSystemResource("inputDay2").file).useLines { lines ->
        lines.fold(0 to 0) { (horizontal, depth), line ->
            val (command, amount) = parseLine(line)

            when (command) {
                Command.Forward -> horizontal + amount to depth
                Command.Down -> horizontal to depth + amount
                Command.Up -> horizontal to depth - amount
            }
        }.let { (horizontal, depth) -> horizontal * depth }
    }

private fun part2() =
    File(ClassLoader.getSystemResource("inputDay2").file).useLines { lines ->
        lines.fold(0 to 0 tto 0) { (horizontal, depth, aim), line ->

            val (command, amount) = parseLine(line)

            when(command){
                Command.Down -> horizontal to depth tto aim + amount
                Command.Up -> horizontal to depth tto aim - amount
                Command.Forward -> horizontal + amount to depth + aim * amount tto aim
            }
        }.let { (horizontal, depth, _) -> horizontal * depth }
    }

private fun parseLine(line: String) =
    line.split(' ').let { (command, amount) -> Command.valueOf(command.uppercaseFirst()) to amount.toInt() }

private infix fun<A,B,C> Pair<A,B>.tto(c: C) = Triple(first, second, c)

private fun String.uppercaseFirst() =
    "${this[0].uppercase()}${substring(1)}"