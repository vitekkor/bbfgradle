//File Delegate.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class Delegate {
   public final int getValue(@Nullable Object t, @NotNull KProperty p) {
      return 1;
   }
}


//File AImpl.java
import kotlin.Metadata;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;

public final class AImpl {
   // $FF: synthetic field
   static final KProperty[] $$delegatedProperties = new KProperty[]{(KProperty)Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(AImpl.class), "prop", "getProp()Ljava/lang/Number;"))};
   @NotNull
   private final Delegate prop$delegate = new Delegate();

   @NotNull
   public final Number getProp() {
      return (Number)this.prop$delegate.getValue(this, $$delegatedProperties[0]);
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
import kotlin.reflect.KProperty

fun box(): String {
    return if(AImpl().prop == 1) "OK" else "fail"
}

