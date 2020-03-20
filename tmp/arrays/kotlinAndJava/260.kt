//File Tester.java
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class Tester {
   @NotNull
   public final String box() {
      S var5 = new S("O", "K");
      String o = var5.component1();
      String k = var5.component2();
      String ok = MainKt.component3(var5);
      String ok2 = this.component4(var5);
      return o + k + ok + ok2;
   }

   @NotNull
   public final String component4(@NotNull S $this$component4) {
      String var10000 = $this$component4.getA() + $this$component4.getB();
      if (var10000 == null) {
         throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
      } else {
         String var2 = var10000;
         byte var3 = 2;
         boolean var4 = false;
         if (var2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
         } else {
            var10000 = var2.substring(var3);
            return var10000;
         }
      }
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// KJS_WITH_FULL_RUNTIME
// WITH_RUNTIME

operator fun S.component3() = ((a + b) as String).substring(2)

fun box() = Tester().box()



//File S.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class S {
   @NotNull
   private final String a;
   @NotNull
   private final String b;

   @NotNull
   public final String component1() {
      return this.a;
   }

   @NotNull
   public final String component2() {
      return this.b;
   }

   @NotNull
   public final String getA() {
      return this.a;
   }

   @NotNull
   public final String getB() {
      return this.b;
   }

   public S(@NotNull String a, @NotNull String b) {
      super();
      this.a = a;
      this.b = b;
   }
}
