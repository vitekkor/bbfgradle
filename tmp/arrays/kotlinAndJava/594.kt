//File Outer.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class Outer {
   @NotNull
   private final String outerProp;
   @NotNull
   private String sideEffects;

   @NotNull
   public final String getOuterProp() {
      return this.outerProp;
   }

   @NotNull
   public final String getSideEffects() {
      return this.sideEffects;
   }

   public final void setSideEffects(@NotNull String var1) {
      this.sideEffects = var1;
   }

   public Outer(@NotNull String x) {
      super();
      this.sideEffects = "";
      this.outerProp = x;
   }

   public final class A1 {
      @NotNull
      private String prop;

      @NotNull
      public final String getProp() {
         return this.prop;
      }

      public final void setProp(@NotNull String var1) {
         this.prop = var1;
      }

      public A1() {
         this.prop = "";
         Outer.this.setSideEffects(Outer.this.getSideEffects() + Outer.this.getOuterProp() + "#" + this.prop + "first");
         Outer.this.setSideEffects(Outer.this.getSideEffects() + this.prop + "#second");
      }

      public A1(@NotNull String x) {
         this();
         this.prop = x + '#' + Outer.this.getOuterProp();
         Outer.this.setSideEffects(Outer.this.getSideEffects() + "#third");
      }

      public A1(int x) {
         this(x + "#" + Outer.this.getOuterProp());
         String var10001 = this.prop;
         this.prop = var10001 + "#int";
         Outer.this.setSideEffects(Outer.this.getSideEffects() + "#fourth");
      }
   }

   public final class A2 {
      @NotNull
      private String prop;

      @NotNull
      public final String getProp() {
         return this.prop;
      }

      public final void setProp(@NotNull String var1) {
         this.prop = var1;
      }

      public A2(@NotNull String x) {
         super();
         this.prop = "";
         Outer.this.setSideEffects(Outer.this.getSideEffects() + Outer.this.getOuterProp() + "#" + this.prop + "first");
         Outer.this.setSideEffects(Outer.this.getSideEffects() + this.prop + "#second");
         this.prop = x + '#' + Outer.this.getOuterProp();
         Outer.this.setSideEffects(Outer.this.getSideEffects() + "#third");
      }

      public A2(int x) {
         this.prop = "";
         Outer.this.setSideEffects(Outer.this.getSideEffects() + Outer.this.getOuterProp() + "#" + this.prop + "first");
         Outer.this.setSideEffects(Outer.this.getSideEffects() + this.prop + "#second");
         String var10001 = this.prop;
         this.prop = var10001 + x + '#' + Outer.this.getOuterProp() + "#int";
         Outer.this.setSideEffects(Outer.this.getSideEffects() + "#fourth");
      }
   }
}


//File Main.kt


fun box(): String {
    val outer1 = Outer("propValue1")
    val a1 = outer1.A1("abc")
    if (a1.prop != "abc#propValue1") return "fail1: ${a1.prop}"
    if (outer1.sideEffects != "propValue1#first#second#third") return "fail1-sideEffects: ${outer1.sideEffects}"

    val outer2 = Outer("propValue2")
    val a2 = outer2.A1(123)
    if (a2.prop != "123#propValue2#propValue2#int") return "fail2: ${a2.prop}"
    if (outer2.sideEffects != "propValue2#first#second#third#fourth") return "fail2-sideEffects: ${outer2.sideEffects}"

    val outer3 = Outer("propValue3")
    val a3 = outer3.A1()
    if (a3.prop != "") return "fail2: ${a3.prop}"
    if (outer3.sideEffects != "propValue3#first#second") return "fail3-sideEffects: ${outer3.sideEffects}"

    val outer4 = Outer("propValue4")
    val a4 = outer4.A2("abc")
    if (a4.prop != "abc#propValue4") return "fail4: ${a4.prop}"
    if (outer4.sideEffects != "propValue4#first#second#third") return "fail4-sideEffects: ${outer4.sideEffects}"

    val outer5 = Outer("propValue5")
    val a5 = outer5.A2(123)
    if (a5.prop != "123#propValue5#int") return "fail5: ${a5.prop}"
    if (outer5.sideEffects != "propValue5#first#second#fourth") return "fail5-sideEffects: ${outer5.sideEffects}"

    return "OK"
}

