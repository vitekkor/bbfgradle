//File A.java
import kotlin.Metadata;

public final class A {
}


//File B.java
import kotlin.Metadata;

public final class B {
}


//File Main.kt


fun foo(parameters: Any?): Any? {
    var payload: Any? = null

    if (parameters != null) {
        if (parameters is A || parameters is B) {
            payload = parameters
        } else {
            payload = "O"
        }
    }

    if (payload is String) {
        payload += "K"
    }

    return payload
}

fun box(): String =
        "${foo(C())}"



//File C.java
import kotlin.Metadata;

public final class C {
}
