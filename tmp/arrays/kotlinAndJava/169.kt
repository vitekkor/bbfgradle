//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

public interface A {
   @Nullable
   Object foo();
}


//File B.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface B {
   @NotNull
   String foo();
}


//File Main.kt


fun bar(x: Any?): String {
    if (x is A) {
        val k = x.foo()
        if (k != "OK") return "fail 1"
    }

    if (x is B) {
        val k = x.foo()
        if (k.length != 2) return "fail 2"
    }

    if (x is A && x is B) {
        return x.foo()
    }

    return "fail 4"
}

fun box(): String = bar(object : A, B { override fun foo() = "OK" })

