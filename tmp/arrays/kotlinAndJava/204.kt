//File Bar.java
import kotlin.Metadata;

public final class Bar {
}


//File Foo.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Foo {
   @NotNull
   String xyzzy(@Nullable Object var1);
}


//File Main.kt


fun buildFoo(bar: Bar.() -> Unit): Foo {
    return object : Foo {
        override fun xyzzy(x: Any?): String {
           (x as? Bar)?.bar()
            return "OK"
        }
    }
}

fun box(): String {
    val foo = buildFoo({})
    return foo.xyzzy(Bar())
}

