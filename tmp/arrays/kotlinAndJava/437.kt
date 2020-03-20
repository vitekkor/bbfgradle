//File A1.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class A1 {
   @NotNull
   private final String prop2;
   @NotNull
   private String prop3;
   @NotNull
   private final String prop1;

   @NotNull
   public final String getProp2() {
      return this.prop2;
   }

   @NotNull
   public final String getProp3() {
      return this.prop3;
   }

   public final void setProp3(@NotNull String var1) {
      this.prop3 = var1;
   }

   @NotNull
   public final String f() {
      return this.prop1 + '#' + this.prop2 + '#' + this.prop3;
   }

   @NotNull
   public final String getProp1() {
      return this.prop1;
   }

   public A1(@NotNull String prop1) {
      super();
      this.prop1 = prop1;
      this.prop2 = "const2";
      this.prop3 = "";
   }

   public A1() {
      this("default");
      this.prop3 = "empty";
   }

   public A1(int x) {
      this(String.valueOf(x));
      this.prop3 = "int";
   }

   @NotNull
   public final String component1() {
      return this.prop1;
   }

   @NotNull
   public final A1 copy(@NotNull String prop1) {
      return new A1(prop1);
   }

   // $FF: synthetic method
   public static A1 copy$default(A1 var0, String var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = var0.prop1;
      }

      return var0.copy(var1);
   }

   @NotNull
   public String toString() {
      return "A1(prop1=" + this.prop1 + ")";
   }

   public int hashCode() {
      String var10000 = this.prop1;
      return var10000 != null ? var10000.hashCode() : 0;
   }

   public boolean equals(@Nullable Object var1) {
      if (this != var1) {
         if (var1 instanceof A1) {
            A1 var2 = (A1)var1;
            if (Intrinsics.areEqual(this.prop1, var2.prop1)) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }
}


//File Main.kt


fun box(): String {
    val a1x = A1("asd")
    if (a1x.f() != "asd#const2#") return "fail1: ${a1x.f()}"
    if (a1x.toString() != "A1(prop1=asd)") return "fail1s: ${a1x.toString()}"
    val a1y = A1()
    if (a1y.f() != "default#const2#empty") return "fail2: ${a1y.f()}"
    if (a1y.toString() != "A1(prop1=default)") return "fail2s: ${a1y.toString()}"
    val a1z = A1(5)
    if (a1z.f() != "5#const2#int") return "fail3: ${a1z.f()}"
    if (a1z.toString() != "A1(prop1=5)") return "fail3s: ${a1z.toString()}"

    val a2x = A2("asd")
    if (a2x.f() != "asd#const2#") return "fail4: ${a2x.f()}"
    val a2y = A2(123.0)
    if (a2y.f() != "default#const2#empty") return "fail5: ${a2y.f()}"
    val a2z = A2(5)
    if (a2z.f() != "5#const2#int") return "fail6: ${a2z.f()}"

    return "OK"
}



//File A2.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class A2 {
   @NotNull
   private String prop1;
   @NotNull
   private String prop2;
   @NotNull
   private String prop3;

   @NotNull
   public final String getProp1() {
      return this.prop1;
   }

   public final void setProp1(@NotNull String var1) {
      this.prop1 = var1;
   }

   @NotNull
   public final String getProp2() {
      return this.prop2;
   }

   public final void setProp2(@NotNull String var1) {
      this.prop2 = var1;
   }

   @NotNull
   public final String getProp3() {
      return this.prop3;
   }

   public final void setProp3(@NotNull String var1) {
      this.prop3 = var1;
   }

   @NotNull
   public final String f() {
      return this.prop1 + '#' + this.prop2 + '#' + this.prop3;
   }

   private A2() {
      this.prop1 = "";
      this.prop2 = "const2";
      this.prop3 = "";
   }

   public A2(@NotNull String arg) {
      this();
      this.prop1 = arg;
   }

   public A2(double x) {
      this();
      this.prop1 = "default";
      this.prop3 = "empty";
   }

   public A2(int x) {
      this(String.valueOf(x));
      this.prop3 = "int";
   }
}
