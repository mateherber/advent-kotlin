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
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)
