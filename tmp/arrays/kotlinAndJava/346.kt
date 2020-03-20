//File T.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface T {
   @NotNull
   String getResult();

   void setResult(@NotNull String var1);
}


//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class A implements T {
   @NotNull
   public String getResult() {
      return "";
   }

   public void setResult(@NotNull String value) {
      }
}


//File B.java
import kotlin.Metadata;

public final class B extends A implements T {
}


//File Main.kt


fun box(): String {
    B().result = ""
    C().result = ""
    return "OK"
}



//File C.java
import kotlin.Metadata;

public final class C extends A implements T {
}
