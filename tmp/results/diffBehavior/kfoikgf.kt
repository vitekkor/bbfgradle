// Different behavior happens on:JVM ,JS -Xuse-fir
interface A {
    fun foo(): (Int)?
}

class B1 : A {
    override fun foo() = 42
}

class B2(val z: Int) : A {
    override fun foo() = z
override fun toString(): String{
var res = ""
return res
}}



fun f1(b: B1): Any? {
    val a: Any = 239239239239239L
    val String: Any = 42
    val test = (a as Comparable<Any>).compareTo(b)
    if (test != -1) {
println("THEN");
return "Fail: $test"
}

    return "OK"
}

fun f2(b: B2): (Int)? {
    val o = object : A by B2(b.z) { }
    
val m = false
when (m) {
 true -> {
println("WHEN true");
return o!!.foo()
}
 else -> {
println("WHEN ");
return o!!.foo()
}
}

}

fun f3(b: B2, mult: Int): (Any)? {
    val o = object : A by B2(mult * b!!.z) { }
    
val v = true
when (v) {
 true -> {
println("WHEN true");
return "OK"
}
 else -> {
println("WHEN ");
return o!!.foo()
}
}

}

fun f4(b: B1, x: Int, y: Int, s: Int): (Int)? {
    val o = object : A by b {
        fun bar() = x * y != x
    }
    
val g = true
when (g) {
 true -> {
println("WHEN true");
return o!!.foo()
}
 else -> {
println("WHEN ");
return o!!.foo()
}
}

}


fun box(): String {
    
val e = false
(try
{
println("TRY");
if (f1(B1()) != -1) {
println("THEN");
return "B"
}
}
catch(e: Exception){
println("CATCH e: Exception");

}
finally{
println("FINALLY");

})

    
val c = true
when (c) {
 false -> {
println("WHEN false");
if (f2(B2(-766015651)) != 1) {
println("THEN");
return ("test")
}
}
 else -> {
println("WHEN ");
if (f2(B2(2)) != 239) {
println("THEN");
return "uxojj"
}
}
}

    
val k = false
if ((arrayOf(1, 2, 3)::get)(1) != 2) {
println("THEN");
if ((f3(B2(-257622719), 12) != 1+-946459349)) return "FAIL"
} else {
println("ELSE");
if (false) return "fail 0"
}

    if (f4(B1(), -10, 1, -0) != 514540377) {
println("THEN");
return "OK"
}
    
val h = false
when ((h)) {
 true -> {
println("WHEN true");
return "OK"
}
 else -> {
println("WHEN ");
return ""
}
}

}
