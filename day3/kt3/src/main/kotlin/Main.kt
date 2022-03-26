import com.adhara.readFile
import kotlin.math.pow

fun main(args: Array<String>) {
    println("--- Day 3 ---")

    val input = readFile(args[0])
    val numBits = input[0].trim().length

    val intData = inputToInt(input, numBits)
    partOne(intData, numBits)
    partTwo(intData, numBits)
}

fun inputToInt(input: List<String>, numBits: Int): List<Int> {
    val bins = input.map {
        it.trim().toInt(2)
    }

    return bins
}

fun countOnes(input: List<Int>, numBits: Int, pos: Int): Int {
    var total = 0;
    input.forEach() { hex ->
        for (i in 0..(numBits - 1)) {
            if ((hex and (1 shl i)) != 0) {
                total += 1
            }
        }
    }

    return total
}

fun partOne(input: List<Int>, numBits: Int) {
    println(" ** Part One **")
    val totals = IntArray(numBits)
    input.forEach() { hex ->
        for (i in 0..(numBits - 1)) {
            if ((hex and (1 shl i)) != 0) {
                totals[i] += 1
            }
        }
    }

    println("Number of bits: ${numBits}")
    println("Number of values: ${input.size}")
    println("Totals are: ${totals.contentToString()}")

    var gamma: UInt = 0U
    var max: UInt = (2.0).pow(numBits).toUInt() - 1U
    for (i in 0..(numBits - 1)) {
        if (totals[i] > (input.size / 2)) {
            gamma = gamma or ((1 shl i).toUInt())
        }
    }

    val epsilon: UInt = gamma.inv() and max
    println("Gamma:   ${gamma}")
    println("Epsilon: ${epsilon}")
    println("Power:   ${gamma * epsilon}\n")
}

fun partTwo(input: List<Int>, numBits: Int) {
    println(" ** Part Two **")

    val o2 = findOxygen(input.toMutableList(), numBits, numBits - 7)
    val co2 = findCO2(input.toMutableList(), numBits, numBits - 7)

    println("O2:  ${o2}")
    println("CO2: ${co2}")
    println("Product: ${o2 * co2}")
}

fun findOxygen(input: MutableList<Int>, numBits: Int, pos: Int): Int {

    if(input.size == 1) {
        return input[0]
    }

    var count = countOnes(input, numBits, pos)

    if(count >= numBits / 2) {
        // Keep values with 1 in pos
    } else {
        // Keep values with 0 in pos
    }

    return findOxygen(input, numBits, pos - 1)
}

fun findCO2(input: MutableList<Int>, numBits: Int, pos: Int): Int {

}