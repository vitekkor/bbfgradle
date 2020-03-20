//File Anno.java
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;

@Retention(RetentionPolicy.RUNTIME)
public @interface Anno {
}


//File Main.kt


@Anno val Int.foo: Int
    get() = this

@Anno val String.foo: Int
    get() = 42

fun box() = if (42.foo == 42 && "OK".foo == 42) "OK" else "Fail"

