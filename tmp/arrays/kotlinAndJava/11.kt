//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;

public final class A {
   // $FF: synthetic field
   static final KProperty[] $$delegatedProperties = new KProperty[]{(KProperty)Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(A.class), "prop", "getProp()I"))};
   private final Delegate prop$delegate = new Delegate();

   private final int getProp() {
      return this.prop$delegate.getValue(this, $$delegatedProperties[0]);
   }

   private final void setProp(int var1) {
      this.prop$delegate.setValue(this, $$delegatedProperties[0], var1);
   }

   @NotNull
   public final String test() {
      if (this.getProp() != 1) {
         return "fail get";
      } else {
         this.setProp(2);
         return this.getProp() != 2 ? "fail set" : "OK";
      }
   }
}


//File Delegate.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class Delegate {
   private int inner = 1;

   public final int getInner() {
      return this.inner;
   }

   public final void setInner(int var1) {
      this.inner = var1;
   }

   public final int getValue(@Nullable Object t, @NotNull KProperty p) {
      return this.inner;
   }

   public final void setValue(@Nullable Object t, @NotNull KProperty p, int i) {
      this.inner = i;
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
import kotlin.reflect.KProperty

fun box(): String {
  return A().test()
}

