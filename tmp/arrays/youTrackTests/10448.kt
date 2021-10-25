// Original bug: KT-4225

var i = 0
var j = 0

fun incI(){
    i++
}

fun incJ(a: Any){
    j++
}

fun foo(f: () -> Unit) = f

fun main(args: Array<String>) {
    val bar = 1
  
    val f = foo {
      incI()
      incJ(if (bar == 2) "A" else "B")
    }
  
    println("i = $i, j = $j")
    f()
    println("i = $i, j = $j")
}
