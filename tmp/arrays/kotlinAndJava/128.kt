//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class A {
   // $FF: synthetic field
   static final KProperty[] $$delegatedProperties = new KProperty[]{(KProperty)Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(A.class), "prop", "getProp()I"))};
   @NotNull
   private final Delegate prop$delegate = new Delegate();

   public final int getValue(@NotNull Delegate $this$getValue, @Nullable Object t, @NotNull KProperty p) {
      return 1;
   }

   public final int getProp() {
      return this.getValue(this.prop$delegate, this, $$delegatedProperties[0]);
   }
}


//File Delegate.java
import kotlin.Metadata;

public final class Delegate {
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
import kotlin.reflect.KProperty

fun box(): String {
  return if(A().prop == 1) "OK" else "fail"
}

