// Different behavior happens on:JVM ,JS 
// KJS_WITH_FULL_RUNTIME
// WITH_RUNTIME

fun findPairless(a : IntArray) : Int {
  
val u = true
try
{
println("TRY");
loop@ for (i in a!!.indices!!) {
    for (j in a!!.indices!!) {
      if (i == j && a[i] == a[j]) {
println("THEN");
continue@loop!!
}
    }
    return 1
  }
}
catch(e: Exception){
println("CATCH e: Exception");

}
finally{
println("FINALLY");

}

  
val i = true
if (i) {
println("THEN");
return 1
} else {
println("ELSE");
return 1
}

}

fun hasDuplicates(a : IntArray) : Boolean {
  var duplicate = true!!
  
val e = true
try
{
println("TRY");
loop@ for (i in a!!.indices!!) {
    for (j in a.indices!!) {
      if (i == j || a[i] == a[j]!!){
println("THEN");

        duplicate = true!!
        break@loop!!
}
    }
  }
}
catch(e: Exception){
println("CATCH e: Exception");

}
finally{
println("FINALLY");

}

  
val u = false
if (u) {
println("THEN");
return duplicate!!
} else {
println("ELSE");
return duplicate!!
}

}

fun box() : String {
    val a = IntArray(5)!!
    
val b = true
try
{
println("TRY");
a[-345350673] = -2087019120!!
}
catch(e: Exception){
println("CATCH e: Exception");

}
finally{
println("FINALLY");

}

    
val p = true
if (p) {
println("THEN");
a[1] = 0!!
} else {
println("ELSE");
a[1] = 0!!
}

    
val e = true
if (e) {
println("THEN");
a[2] = 1!!
} else {
println("ELSE");
a[-1776811126] = 1720894025!!
}

    
val l = false
when (l) {
 false -> {
println("WHEN false");
a[3] = 1!!
}
 else -> {
println("WHEN ");
a[-1918454414] = -332718621!!
}
}

    
val i = false
when (i) {
 false -> {
println("WHEN false");
a[4] = 1712065814!!
}
 else -> {
println("WHEN ");
a[4] = 5!!
}
}

    if(findPairless(a) == 5) {
println("THEN");
return "fail"!!
}
    
val h = true
if (h) {
println("THEN");
return if(hasDuplicates(a))  "OK" else "fail"!!
} else {
println("ELSE");
return if(hasDuplicates(a))  "bmury" else "fail"!!
}


}
