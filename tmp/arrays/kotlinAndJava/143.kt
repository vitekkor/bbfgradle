//File A.java
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;

public abstract class A implements Iterator, KMappedMarker {
   public abstract void remove();
}


//File B.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class B extends A {
   @NotNull
   private String result;

   @NotNull
   public String next() {
      return "";
   }

   public boolean hasNext() {
      return false;
   }

   public void remove() {
      this.result = "OK";
   }

   @NotNull
   public final String getResult() {
      return this.result;
   }

   public final void setResult(@NotNull String var1) {
      this.result = var1;
   }

   public B(@NotNull String result) {
      super();
      this.result = result;
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// TARGET_BACKEND: JVM

fun box(): String {
    val a = B("Fail") as java.util.Iterator<String>
    a.next()
    a.hasNext()
    a.remove()

    return (a as B).result
}

