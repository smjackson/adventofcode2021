import java.io.File
import java.io.InputStream

enum class Direction() {
    FORWARD,
    UP,
    DOWN
}

data class Command(val dir: Direction, val amount: Int)

fun readInputFile(filename: String): List<String> {
    val inputStream: InputStream = File(filename).inputStream()

    val rawData = inputStream.bufferedReader().use { it.readText() }

    return rawData.split("\n").map { it.trim() }
}

fun main(args: Array<String>) {
    println("--- Day 2 ---")

    val rawData = readInputFile(args[0])
    var commands = ArrayDeque<Command>()

    // Build command list
    rawData.forEach {
        val item = it.split(" ")
        val x = Command(Direction.valueOf(item[0].uppercase()), item[1].toInt())
        commands.add(Command(Direction.valueOf(item[0].uppercase()), item[1].toInt()))
    }

    println("Found ${commands.size} commands")

    partOne(commands)
    partTwo(commands)
}

fun partOne(cmds: ArrayDeque<Command>): Unit {
    var depth = 0
    var pos = 0

    println("-- Part One --")

    cmds.forEach() {
        when (it.dir) {
            Direction.FORWARD -> pos = pos + it.amount
            Direction.UP -> depth = depth - it.amount
            Direction.DOWN -> depth = depth + it.amount
        }
    }

    println("Depth: ${depth}   Position: ${pos}")
    println("Product: ${depth * pos}\n\n")
}

fun partTwo(cmds: ArrayDeque<Command>): Unit {
    var depth = 0
    var pos = 0
    var aim = 0

    println("-- Part Two --")
    cmds.forEach() {
        when (it.dir) {
            Direction.FORWARD -> {
                pos = pos + it.amount
                depth = depth + (aim * it.amount)
            }
            Direction.UP -> aim = aim - it.amount
            Direction.DOWN -> aim = aim + it.amount
        }
    }

    println("Depth: ${depth}   Position: ${pos}")
    println("Product: ${depth * pos}")
}