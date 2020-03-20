//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class A {
   private final Object o;
   private final Object init_k;

   private final Object k() {
      return this.init_k;
   }

   @NotNull
   public final String getOk() {
      return this.o + String.valueOf(this.k());
   }

   public A(Object init_o, Object init_k) {
      this.init_k = init_k;
      this.o = init_o;
   }
}


//File Main.kt


fun box(): String {
    val a = A("O", "K")
    return a.getOk()
}

