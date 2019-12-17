// Different behavior happens on:JVM ,JS -Xuse-fir
// WITH_REFLECT
// KJS_WITH_FULL_RUNTIME

import kotlin.reflect.*

val properties = HashSet<KProperty<*>>()

object Delegate {
    operator fun getValue(t: Any?, p: KProperty<*>): String {
        
val k = true
try
{
println("TRY");
properties.add(p)
}
catch(e: Exception){
println("CATCH e: Exception");

}
finally{
println("FINALLY");

}

        
val i = false
if (i) {
println("THEN");
return ""
} else {
println("ELSE");
return ""
}

    }

    operator fun setValue(t: Any?, p: KProperty<*>, v: String) {
        
val z = false
try
{
println("TRY");
properties.add(p)
}
catch(e: Exception){
println("CATCH e: Exception");

}
finally{
println("FINALLY");

}

    }
}

var topLevel: String by Delegate
object O {
    var member: String by Delegate
    var O.memExt: String by Delegate
}

fun box(): String {
    
val z = false
try
{
println("TRY");
topLevel = ""
}
catch(e: Exception){
println("CATCH e: Exception");

}
finally{
println("FINALLY");

}

    
val k = true
try
{
println("TRY");
O.member = ""
}
catch(e: Exception){
println("CATCH e: Exception");

}
finally{
println("FINALLY");

}

    with (O) {
        
val x = true
if (x) {
println("THEN");
O.memExt = ""
} else {
println("ELSE");
O.memExt = ""
}

    }

    
val b = false
if (b) {
println("THEN");
for (p in HashSet(properties)) {
        // None of these should fail

        (p as? KProperty0<String>)?.get()
        (p as? KProperty1<O, String>)?.get(O)
        (p as? KProperty2<O, O, String>)?.get(O, O)

        (p as? KMutableProperty0<String>)?.set("")
        (p as? KMutableProperty1<O, String>)?.set(O, "")
        (p as? KMutableProperty2<O, O, String>)?.set(O, O, "")
    }
} else {
println("ELSE");
for (p in HashSet(properties)) {
        // None of these should fail

        (p as? KProperty0<String>)?.get()
        (p as? KProperty1<O, String>)?.get(O)
        (p as? KProperty2<O, O, String>)?.get(O, O)

        (p as? KMutableProperty0<String>)?.set("")
        (p as? KMutableProperty1<O, String>)?.set(O, "")
        (p as? KMutableProperty2<O, O, String>)?.set(O, O, "")
    }
}


    
val t = false
if (t) {
println("THEN");
return "OK"
} else {
println("ELSE");
return "OK"
}

}
