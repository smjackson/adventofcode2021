import java.io.File

fun readFile(filename: String): List<String> {
    return File(filename).readLines()
}

fun main(args: Array<String>) {
    println("--- Day 3 ---")

    val input = readFile(args[0])

    partOne(input)
}

fun partOne(input: List<String>)
{
    val numBits = input[0].trim().length
    val totals = IntArray(numBits)
    val bins = input.map {
        val hex = it.trim().toInt(2)
        for (i in 0..(numBits-1)) {
            if((hex and (1 shl i)) != 0) {
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

        max = max or ((1 shl i).toUInt())
    }

    val epsilon: UInt = (gamma.inv() and max).toUInt()
    println("Gamma:   ${gamma}")
    println("Epsilon: ${epsilon}")
    println("Power:   ${gamma * epsilon}")

}