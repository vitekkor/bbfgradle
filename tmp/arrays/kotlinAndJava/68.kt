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

   public abstract class A1 {
      @NotNull
      private String parentProp;

      @NotNull
      public final String getParentProp() {
         return this.parentProp;
      }

      public final void setParentProp(@NotNull String var1) {
         this.parentProp = var1;
      }

      protected A1(@NotNull String x) {
         super();
         this.parentProp = "";
         Outer.this.setSideEffects(Outer.this.getSideEffects() + Outer.this.getOuterProp() + "#" + this.parentProp + "first");
         Outer.this.setSideEffects(Outer.this.getSideEffects() + this.parentProp + "#third");
         this.parentProp = x + '#' + Outer.this.getOuterProp();
         Outer.this.setSideEffects(Outer.this.getSideEffects() + "#second#");
      }

      protected A1(int x) {
         this(x + "#" + Outer.this.getOuterProp());
         String var10001 = this.parentProp;
         this.parentProp = var10001 + "#int";
         Outer.this.setSideEffects(Outer.this.getSideEffects() + "fourth#");
      }
   }

   public final class A2 extends Outer.A1 {
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
         super(x + "#" + Outer.this.getOuterProp());
         this.prop = "";
         Outer.this.setSideEffects(Outer.this.getSideEffects() + Outer.this.getOuterProp() + "#" + this.prop + "fifth");
         Outer.this.setSideEffects(Outer.this.getSideEffects() + this.prop + "#seventh");
         this.prop = x + '#' + Outer.this.getOuterProp();
         Outer.this.setSideEffects(Outer.this.getSideEffects() + "#sixth");
      }

      public A2(int x) {
         super(x + 1);
         this.prop = "";
         Outer.this.setSideEffects(Outer.this.getSideEffects() + Outer.this.getOuterProp() + "#" + this.prop + "fifth");
         Outer.this.setSideEffects(Outer.this.getSideEffects() + this.prop + "#seventh");
         String var10001 = this.prop;
         this.prop = var10001 + x + '#' + Outer.this.getOuterProp() + "#int";
         Outer.this.setSideEffects(Outer.this.getSideEffects() + "#eighth");
      }
   }
}


//File Main.kt


fun box(): String {
    val outer1 = Outer("propValue1")
    val a1 = outer1.A2("abc")
    if (a1.parentProp != "abc#propValue1#propValue1") return "fail1: ${a1.parentProp}"
    if (a1.prop != "abc#propValue1") return "fail2: ${a1.prop}"
    if (outer1.sideEffects != "propValue1#first#third#second#propValue1#fifth#seventh#sixth") return "fail1-sideEffects: ${outer1.sideEffects}"

    val outer2 = Outer("propValue2")
    val a2 = outer2.A2(123)
    if (a2.parentProp != "124#propValue2#propValue2#int") return "fail3: ${a2.parentProp}"
    if (a2.prop != "123#propValue2#int") return "fail4: ${a2.prop}"
    if (outer2.sideEffects != "propValue2#first#third#second#fourth#propValue2#fifth#seventh#eighth") return "fail2-sideEffects: ${outer2.sideEffects}"

    return "OK"
}

