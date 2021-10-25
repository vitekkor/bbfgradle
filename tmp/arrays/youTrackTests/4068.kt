// Original bug: KT-16678

import java.util.Arrays
import java.util.stream.Collectors
import java.util.stream.Stream

fun main(args: Array<String>) {
    val stream: Stream<Int> = Arrays.asList(12, 1, 5).stream()
    println(stream.collect(Collectors.toList()))
    /*

Error:(12, 4) Overload resolution ambiguity: 
@InlineOnly public inline fun println(message: Any?): Unit defined in kotlin.io 
@InlineOnly public inline fun println(message: Boolean): Unit defined in kotlin.io 
@InlineOnly public inline fun println(message: Byte): Unit defined in kotlin.io 
@InlineOnly public inline fun println(message: Char): Unit defined in kotlin.io 
@InlineOnly public inline fun println(message: CharArray): Unit defined in kotlin.io 
@InlineOnly public inline fun println(message: Double): Unit defined in kotlin.io 
@InlineOnly public inline fun println(message: Float): Unit defined in kotlin.io 
@InlineOnly public inline fun println(message: Int): Unit defined in kotlin.io 
@InlineOnly public inline fun println(message: Long): Unit defined in kotlin.io 
@InlineOnly public inline fun println(message: Short): Unit defined in kotlin.io

     */
}
