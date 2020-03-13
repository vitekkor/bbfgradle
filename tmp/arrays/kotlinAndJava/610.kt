//File Delegate.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;

public final class Delegate {
   public final int getValue(@NotNull F.A t, @NotNull KProperty p) {
      return 1;
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
import kotlin.reflect.KProperty

fun box(): String {
    return if(F().foo() == 1) "OK" else "fail"
}



//File F.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference2Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;

public final class F {
   // $FF: synthetic field
   static final KProperty[] $$delegatedProperties = new KProperty[]{(KProperty)Reflection.property2(new PropertyReference2Impl(Reflection.getOrCreateKotlinClass(F.class), "prop", "getProp(LF$A;)I"))};
   @NotNull
   private final Delegate prop$delegate = new Delegate();

   public final int getProp(@NotNull F.A $this$prop) {
      return this.prop$delegate.getValue($this$prop, $$delegatedProperties[0]);
   }

   public final int foo() {
      return this.getProp(new F.A());
   }

   public static final class A {
   }
}
