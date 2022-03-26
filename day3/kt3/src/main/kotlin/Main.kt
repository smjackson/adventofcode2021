import java.io.File

fun readFile(filename: String): List<String> {
    return File(filename).readLines()
}

fun main(args: Array<String>) {
    println("--- Day 3 ---")

    val input = readFile(args[0])
    val numBits = input[0].trim().length

    val intData = inputToInt(input, numBits)
    partOne(intData, numBits)
}

fun inputToInt(input: List<String>, numBits: Int): List<Int>
{
    val bins = input.map {
        it.trim().toInt(2)
    }

    return bins
}

fun partOne(input: List<Int>, numBits: Int)
{
    val totals = IntArray(numBits)
    input.forEach() { hex ->
        for (i in 0..(numBits - 1)) {
            if ((hex and (1 shl i)) != 0) {
                totals[i] = totals[i] + 1
            }
        }
    }

    println("Number of bits: ${numBits}")
    println("Number of values: ${input.size}")
    println("Totals are: ${totals.contentToString()}")

    var gamma: UInt = 0U
    var max: UInt = 0U
    for(i in 0 .. (numBits-1)) {
        if(totals[i] > (input.size / 2)) {
            gamma = gamma or ((1 shl i).toUInt())
        }
        // Build max value; in a silly way
        max = max or ((1 shl i).toUInt())
    }

    val epsilon: UInt = (gamma.inv() and max).toUInt()
    println("Gamma:   ${gamma}")
    println("Epsilon: ${epsilon}")
    println("Power:   ${gamma * epsilon}")

}