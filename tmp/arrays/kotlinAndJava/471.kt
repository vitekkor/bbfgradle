//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;

public final class A {
   // $FF: synthetic field
   static final KProperty[] $$delegatedProperties = new KProperty[]{(KProperty)Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(A.class), "prop", "getProp()I"))};
   @NotNull
   private final Delegate prop$delegate = new Delegate();

   public final int getProp() {
      return MainKt.getValue(this.prop$delegate, this, $$delegatedProperties[0]);
   }
}


//File Delegate.java
import kotlin.Metadata;

public final class Delegate {
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
import kotlin.reflect.KProperty

operator fun Delegate.getValue(t: Any?, p: KProperty<*>): Int = 1

fun box(): String {
  return if(A().prop == 1) "OK" else "fail"
}

