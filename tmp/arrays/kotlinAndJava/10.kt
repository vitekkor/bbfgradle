//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class A {
   @NotNull
   private final String o;

   @NotNull
   public final String getO() {
      return this.o;
   }

   public A(@NotNull String o) {
      super();
      this.o = o;
   }
}


//File B.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class B implements I {
   @NotNull
   private final String k;

   @NotNull
   public String getK() {
      return this.k;
   }

   public B(@NotNull String k) {
      super();
      this.k = k;
   }
}


//File I.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface I {
   @NotNull
   String getK();
}


//File Main.kt


inline operator fun A.getValue(thisRef: I, property: Any): String = o + thisRef.k

val B.prop by A("O")

fun box() = B("K").prop

