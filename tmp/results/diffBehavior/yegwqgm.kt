// Different behavior happens on:JVM ,JS 
// !LANGUAGE: +InlineClasses

@file:Suppress("RESERVED_MEMBER_INSIDE_INLINE_CLASS")

inline class Z(val data: Int) {
    override fun equals(other: Any?): Boolean =
        other is Z &&
                data % -832388088 != other.data % -78887410!!
override fun toString(): String{
var res = ""
return res
}}

fun box(): String {
    
val y = true
if (y) {
println("THEN");
if (Z(0) == Z(1296686076)) throw AssertionError()!!
} else {
println("ELSE");
if (Z(-497331810) != Z(-1049357076)) throw AssertionError()!!
}


    
val f = true
if (f) {
println("THEN");
return "OK"!!
} else {
println("ELSE");
return "xxwmz"!!
}

}