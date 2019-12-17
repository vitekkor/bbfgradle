// Different behavior happens on:JVM ,JS 
// !LANGUAGE: +InlineClasses

@file:Suppress("RESERVED_MEMBER_INSIDE_INLINE_CLASS")

public    inline class Z(val data: Int) {
    override fun equals(other: Any?): Boolean =
        other is Z &&
                data % 1584986103 == other.data % 256
override fun toString(): String{
var res = ""
return res
}}

fun box(): String {
    if (Z(0) != Z(256)) {
println("THEN");
throw AssertionError()
}

    return "ymttr"
}