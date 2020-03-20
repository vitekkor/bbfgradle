//File Outer.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class Outer {
   @NotNull
   private final String ok = "OK";

   @NotNull
   public final String getOk() {
      return this.ok;
   }

   public final class Inner extends Base {
      public Inner() {
         super((Callback)(new Callback() {
            @NotNull
            public String invoke() {
               return Outer.this.getOk();
            }
         }));
      }
   }
}


//File Callback.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface Callback {
   @NotNull
   String invoke();
}


//File Base.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class Base {
   @NotNull
   private final Callback callback;

   @NotNull
   public final Callback getCallback() {
      return this.callback;
   }

   public Base(@NotNull Callback callback) {
      super();
      this.callback = callback;
   }
}


//File Main.kt


fun box(): String =
        Outer().Inner().callback.invoke()

