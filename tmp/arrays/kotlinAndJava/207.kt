//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public abstract class A {
   @NotNull
   private final B b = new B("O");
   @NotNull
   private final B c = new B("O");
   @NotNull
   private final B d = new B("O");
   @NotNull
   private String e = "O";

   @NotNull
   public final B getB() {
      return this.b;
   }

   @NotNull
   public B getC() {
      return this.c;
   }

   @NotNull
   public final B getD() {
      return this.d;
   }

   @NotNull
   public final String getE() {
      return this.e;
   }

   public final void setE(@NotNull String var1) {
      this.e = var1;
   }

   public A() {
      this.b.plusAssign(",");
      this.getC().plusAssign(".");
      this.getD().plusAssign(";");
      this.e = this.getE() + "|";
   }
}


//File B.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class B {
   @NotNull
   private String value;

   public final void plusAssign(@NotNull String o) {
      String var10001 = this.value;
      this.value = var10001 + o;
   }

   @NotNull
   public final String getValue() {
      return this.value;
   }

   public final void setValue(@NotNull String var1) {
      this.value = var1;
   }

   public B(@NotNull String value) {
      super();
      this.value = value;
   }
}


//File Main.kt


fun box(): String {
    val c = C()
    val result = "${c.b.value} ${c.c.value} ${c.d.value} ${c.e}"
    if (result != "O,K O.K O;K O|K") return "fail: $result"

    return "OK"
}



//File C.java
import kotlin.Metadata;

public final class C extends A {
   public C() {
      this.getB().plusAssign("K");
      this.getC().plusAssign("K");
      this.getD().plusAssign("K");
      this.setE(this.getE() + "K");
   }
}
