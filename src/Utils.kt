import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun String.inputStrings() = File("inputs", "$this.txt").readLines()

/**
 * Reads lines from the given input txt file and converts them to integers.
 */
fun String.inputInts() = inputStrings().map { it.toInt() }

/**
 * Maps the given string list to string integer pairs.
 */
fun List<String>.toStringIntPairs() =
    map { input -> input.split(" ").let { split -> split[0] to split[1].toInt() } }

/**
 * Maps the given binary string representations to integers.
 */
fun List<String>.binaryToInt() =
    map { Integer.parseInt(it, 2); }

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)
