//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class A {
   @NotNull
   private final String value;

   @NotNull
   public final String getValue() {
      return this.value;
   }

   public A(@NotNull String value) {
      super();
      this.value = value;
   }
}


//File Main.kt


fun A.test(): String {
    val o = object  {
        val z: String
        init {
            val x = value + "K"
            z = x
        }
    }
    return o.z
}

fun box(): String {
    return A("O").test()
}

