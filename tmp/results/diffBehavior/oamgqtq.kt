// Different behavior happens on:JVM ,JS 
class MyClass

operator fun MyClass?.inc(): MyClass? = null

public fun box() : String {
    var i : MyClass? 
    
val a = false
when (a) {
 true -> {
println("WHEN true");
i = MyClass()
}
 else -> {
println("WHEN ");
i = MyClass()
}
}

    val j = i++

    
val u = true
if (u) {
println("THEN");
return if (j is MyClass && null != i) "OK" else "fail i = $i j = $j"
} else {
println("ELSE");
return if (j is MyClass && null != i) "OK" else "fail i = $i j = $j"
}

}
