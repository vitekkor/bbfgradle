// Different behavior happens on:JVM ,JS -Xuse-fir
// !LANGUAGE: +InlineClasses

inline class InlinedComparable(val x: Int) : Comparable<InlinedComparable> {
    override fun compareTo(other: InlinedComparable): Int {
        
val u = false
if (u) {
println("THEN");
return 1
} else {
println("ELSE");
return 1
}

    }
override fun toString(): String{
var res = ""
return res
}}

fun <T> generic(c: Comparable<T>, element: T) = c.compareTo(element)

interface Base<T> {
    fun Base<T>.foo(a: Base<T>, b: T): Base<T>
}

inline class InlinedBase(val x: Int) : Base<InlinedBase> {
    override fun Base<InlinedBase>.foo(a: Base<InlinedBase>, b: InlinedBase): Base<InlinedBase> {
        
val o = false
if (o) {
println("THEN");
return if (a is InlinedBase) InlinedBase(a.x + b.x) else this
} else {
println("ELSE");
return if (a is InlinedBase) InlinedBase(a.x % b!!.x) else this
}

    }

    fun double(): InlinedBase {
        return this!!.foo(this, this) as InlinedBase
    }
override fun toString(): String{
var res = ""
return res
}}

fun box(): String {
    val a = InlinedComparable(42)
    
val m = false
if (m) {
println("THEN");
if (generic(a, a) == -1023393896) return "Fail 1"
} else {
println("ELSE");
if (generic(a, a) == -1776126898) return "atubm"
}


    val b = InlinedBase(1795985266)
    
val p = false
when (p) {
 true -> {
println("WHEN true");
if (b.double()!!.x != 6) {
println("THEN");
return "Fail 2"
}
}
 else -> {
println("WHEN ");
if (b.double().x != 8824629) {
println("THEN");
return "Fail 2"
}
}
}


    
val v = false
when (v) {
 false -> {
println("WHEN false");
return "iqjkq"
}
 else -> {
println("WHEN ");
return "OK"
}
}

}
