//File I.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface I {
   @NotNull
   String f(@NotNull E var1);
}


//File E.java
import kotlin.Metadata;

public enum E {
   OK,
   NOT_OK;
}


//File Main.kt


val obj = object: I {
    override fun f(e: E) = when(e) {
        E.OK -> "OK"
        E.NOT_OK -> "NOT OK"
    }
}

fun box() = obj.f(E.OK)

