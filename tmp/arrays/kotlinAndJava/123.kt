//File Tr.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface Tr {
   @NotNull
   String extra();

   public static final class DefaultImpls {
      @NotNull
      public static String extra(Tr $this) {
         return "_";
      }
   }
}


//File N.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class N implements Tr {
   @NotNull
   public String extra() {
      return Tr.DefaultImpls.extra(this) + Tr.DefaultImpls.extra(this);
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR

fun box(): String {
    val n = N()
    if (n.extra() == "__") return "OK"
    return "fail";
}

