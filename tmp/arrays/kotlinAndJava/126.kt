//File IFoo.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface IFoo {
   @NotNull
   String foo();
}


//File IBar.java
import kotlin.Metadata;

public interface IBar {
}


//File Main.kt


private fun createAnonObject() =
        object : IFoo, IBar {
            override fun foo() = "foo"
            fun qux(): String = "qux"
        }

fun useAnonObject() {
    createAnonObject().foo()
    createAnonObject().qux()
}

fun box(): String {
    if (createAnonObject().foo() != "foo") return "fail 1"
    if (createAnonObject().qux() != "qux") return "fail 2"
    return "OK"
}

