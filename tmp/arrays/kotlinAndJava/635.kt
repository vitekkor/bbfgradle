//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public class A {
   @NotNull
   public final String foo() {
      return "OK";
   }
}


//File Main.kt


fun box() = object : A() {
    fun bar() = super<A>.foo()
}.bar()

