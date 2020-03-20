//File Second.java
import kotlin.Metadata;

public interface Second {
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// WITH_RUNTIME

val b: First by lazy {
    object : First {   }
}

private val withoutType by lazy {
    object : First { }
}

private val withTwoSupertypes by lazy {
    object : First, Second { }
}

fun box(): String {
    b
    withoutType
    withTwoSupertypes
    return "OK"
}



//File First.java
import kotlin.Metadata;

public interface First {
}
