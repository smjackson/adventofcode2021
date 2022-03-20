import java.io.File
import java.io.InputStream

fun readInputFile(filename: String): List<Int> {
    val inputStream: InputStream = File(filename).inputStream()

    val rawData = inputStream.bufferedReader().use { it.readText() }

    return rawData.split("\n").map { it.trim().toInt() }
}

fun main(args: Array<String>) {
    println("Day 1")
    println("--------")

    val inputData = readInputFile(args[0])

    val simple = findNumIncreases(inputData)
    println("${simple} increases (point-by-point)")

    val sliding = slidingWindow(inputData, 3)
    println("${sliding} increases using sliding window")

}

fun findNumIncreases(inputData: List<Int>): Int
{
    var last: Int = inputData[0]
    var total = 0
    inputData.forEach { num ->
        if(num > last) {
            total += 1
        }
        last = num
    }

    return total
}

fun slidingWindow(inputData: List<Int>, windowSize: Int): Int
{
    val window = ArrayDeque<Int>()
    var increases = 0
    var lastSum = -1

    inputData.forEach { num ->
        window.add(num)

        if(window.size == windowSize) {
            var sum = window.sum()
            if(lastSum > 0 && sum > lastSum) {
                lastSum = lastSum + 1
                increases = increases + 1
            }
            lastSum = sum
            window.removeFirst()
        }
    }

    return increases
}