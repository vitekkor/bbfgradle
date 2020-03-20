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

   public final String component4(@NotNull S $this$component4) {
      String var10000 = $this$component4.getA() + $this$component4.getB();
      if (var10000 == null) {
         throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
      } else {
         return var10000.substring(2);
      }
   }
}


//File Main.kt


operator fun S.component3() = ((a + b) as java.lang.String).substring(2)

fun box() = Tester().box()

// 1 NEW S



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
