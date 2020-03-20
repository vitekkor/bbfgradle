//File A.java
import kotlin.Metadata;

public interface A {
}


//File B.java
import kotlin.Metadata;

public interface B {
}


//File Main.kt


fun <T> T.foo(): String where T : A, T : B {
    return "OK"
}

fun box(): String {
    return C().foo()
}



//File C.java
import kotlin.Metadata;

public final class C implements A, B {
}
