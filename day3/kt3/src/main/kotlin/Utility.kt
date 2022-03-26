package com.adhara
import java.io.File

fun readFile(filename: String): List<String> {
    return File(filename).readLines()
}
