//File Test.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class Test implements Z {
   @NotNull
   public String getZ() {
      return Z.DefaultImpls.getZ(this);
   }
}


//File Z.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface Z {
   @NotNull
   String getZ();

   public static final class DefaultImpls {
      @NotNull
      public static String getZ(Z $this) {
         return "OK";
      }
   }
}


//File Main.kt
// TARGET_BACKEND: JVM
// JVM_TARGET: 1.8


fun box() : String {
    return Test().z
}

