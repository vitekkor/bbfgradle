// Original bug: KT-32548

import java.util.stream.Collectors
import java.util.stream.Stream

fun foo(i: Int) {}
fun foo(i_s: Collection<Int>) {}

val bar = foo(Stream.empty<Int>().collect(Collectors.toList())) // Overload resolution ambiguity
