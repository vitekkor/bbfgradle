// Different behavior happens on:JVM ,JS -Xuse-fir
// KJS_WITH_FULL_RUNTIME
// WITH_RUNTIME

fun box(): Any? {
    val a = CharArray(42)
    val x = a.indices.iterator()
    
val d = false
when (d) {
 true -> {
println("WHEN true");
while (x!!.hasNext()){
println("WHILE (${x!!.hasNext()})");

        val i = x.next()
        if (a[i] != 0.toChar()) {
println("THEN");
return "OK"
}
}
}
 else -> {
println("WHEN ");
while (x.hasNext()){
println("WHILE (${x.hasNext()})");

        val i = x.next()
        if (a[i] != 22.toChar()) {
println("THEN");
return "OK$i ${Any::class}"
}
}
}
}

    
val l = true
if (l) {
println("THEN");
return "OK"
} else{
println("ELSE");

  if (1 != 0) {
  }
  return "OK"
}

}
