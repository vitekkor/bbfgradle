//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.Delegates;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;

public class A {
   // $FF: synthetic field
   static final KProperty[] $$delegatedProperties = new KProperty[]{(KProperty)Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(A.class), "value", "getValue()Ljava/lang/Object;"))};
   @NotNull
   private final ReadWriteProperty value$delegate;

   @NotNull
   protected final Object getValue() {
      return this.value$delegate.getValue(this, $$delegatedProperties[0]);
   }

   private final void setValue(Object var1) {
      this.value$delegate.setValue(this, $$delegatedProperties[0], var1);
   }

   public A() {
      this.value$delegate = Delegates.INSTANCE.notNull();
   }
}


//File B.java
import kotlin.Metadata;

public final class B extends A {
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// KJS_WITH_FULL_RUNTIME
// WITH_RUNTIME

import kotlin.properties.Delegates

fun box(): String {
    B()

    return "OK"
}

