//File NotImplemented.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class NotImplemented {
   public final Object getValue(@Nullable Object thisRef, @NotNull KProperty prop) {
      MainKt.notImplemented();
      throw null;
   }

   @NotNull
   public final Void setValue(@Nullable Object thisRef, @NotNull KProperty prop, Object value) {
      MainKt.notImplemented();
      throw null;
   }
}


//File Test.java
import kotlin.Metadata;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;

public final class Test {
   // $FF: synthetic field
   static final KProperty[] $$delegatedProperties = new KProperty[]{(KProperty)Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Test.class), "x", "getX()I"))};
   @NotNull
   private final NotImplemented x$delegate = new NotImplemented();

   public final int getX() {
      return ((Number)this.x$delegate.getValue(this, $$delegatedProperties[0])).intValue();
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
import kotlin.reflect.KProperty

// java.lang.VerifyError: (class: NotImplemented, method: get signature: (Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;) Unable to pop operand off an empty stack

fun notImplemented() : Nothing = notImplemented()

fun box(): String {
    Test()
    return "OK"
}

