//File T.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface T {
   @NotNull
   String foo();
}


//File Main.kt


val o = object : T {
    val a = "OK"
    val f = {
        a
    }()

    override fun foo() = f
}

fun box() = o.foo()

