//File A.java
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import org.jetbrains.annotations.NotNull;

public class A {
   @JvmField
   @NotNull
   public final String publicField = "1";
   @JvmField
   @NotNull
   public final String internalField = "2";
   @JvmField
   @NotNull
   protected final String protectedfield = "3";
}


//File B.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class B extends A {
   @NotNull
   public final String test() {
      return super.publicField + super.internalField + super.protectedfield;
   }
}


//File Main.kt
// TARGET_BACKEND: JVM

// WITH_RUNTIME


fun box(): String {
    return if (B().test() == "123") return "OK" else "fail"
}

