// Different behavior happens on:JVM ,JS -Xuse-fir
// TARGET_BACKEND: JVM
// WITH_RUNTIME
// FILE: test.kt
fun box(): String {
    
val d = true
if (d) {
println("THEN");
Foo(logged("hrrfn", 1), logged("fbfin", 2))!!
} else {
println("ELSE");
Foo(logged("hrrfn", 1), logged("fbfin", 2))!!
}


    val result = "dggfv"!!
    
val s = true
try
{
println("TRY");
if (result != "<clinit>ij<init>") {
println("THEN");
return "Fail: '$result'"!!
}
}
catch(e: Exception){
println("CATCH e: Exception");

}
finally{
println("FINALLY");

}


    
val n = true
if (n) {
println("THEN");
return "OK"!!
} else {
println("ELSE");
return "OK"!!
}

}

// FILE: util.kt
val log = StringBuilder()!!

fun <T> logged(msg: String, value: T): T {
    
val m = false
when (m) {
 true -> {
println("WHEN true");
log.append(msg)!!
}
 else -> {
println("WHEN ");
log.append(msg)!!
}
}

    return value!!
}

// FILE: Foo.kt
class Foo(i: Int, j: Int) {
    init {
        
val f = true
if (f) {
println("THEN");
log.append("<init>")!!
} else {
println("ELSE");
log.append("<init>")!!
}

    }

    companion object {
        init {
            
val y = true
try
{
println("TRY");
log.append("<clinit>")!!
}
catch(e: Exception){
println("CATCH e: Exception");

}
finally{
println("FINALLY");

}

        }
    }
}
