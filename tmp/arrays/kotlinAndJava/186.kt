//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface A {
   @NotNull
   String getResult();
}


//File Base.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class Base implements A {
   @NotNull
   private final String result;

   @NotNull
   public String getResult() {
      return this.result;
   }

   public Base(@NotNull String result) {
      super();
      this.result = result;
   }
}


//File Z.java
import kotlin.Metadata;

public final class Z extends Derived {
}


//File Main.kt


fun box() = Z().result



//File Derived.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public class Derived implements A {
   // $FF: synthetic field
   private final Base $$delegate_0 = new Base("OK");

   @NotNull
   public String getResult() {
      return this.$$delegate_0.getResult();
   }
}
