//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class A {
   @NotNull
   private String cond1;
   @NotNull
   private String cond2;
   @Nullable
   private final String prop1;
   @Nullable
   private final String prop2;
   @NotNull
   private final String p;

   @NotNull
   public final String getCond1() {
      return this.cond1;
   }

   public final void setCond1(@NotNull String var1) {
      this.cond1 = var1;
   }

   @NotNull
   public final String getCond2() {
      return this.cond2;
   }

   public final void setCond2(@NotNull String var1) {
      this.cond2 = var1;
   }

   @Nullable
   public final String getProp1() {
      return this.prop1;
   }

   @Nullable
   public final String getProp2() {
      return this.prop2;
   }

   public final boolean cond1(@NotNull String p) {
      this.cond1 = "cond1";
      return Intrinsics.areEqual(p, "test");
   }

   public final boolean cond2(@NotNull String p) {
      this.cond2 = "cond2";
      return Intrinsics.areEqual(p, "test");
   }

   @NotNull
   public final String getP() {
      return this.p;
   }

   public A(@NotNull String p, @NotNull String p1, @NotNull String p2) {
      super();
      this.p = p;
      this.cond1 = "";
      this.cond2 = "";
      this.prop1 = this.cond1(this.p) ? p1 : null;
      this.prop2 = this.cond2(this.p) ? p2 : null;
   }
}


//File Main.kt


fun box(): String {
    val a = A("test", "OK", "fail")

    if (a.cond1 != "cond1") return "fail 2 : ${a.cond1}"

    if (a.cond2 != "cond2") return "fail 3 : ${a.cond2}"

    return "OK"
}

