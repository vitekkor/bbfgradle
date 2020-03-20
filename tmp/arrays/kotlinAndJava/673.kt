//File Test.java
import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.Delegates;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;

public final class Test {
   // $FF: synthetic field
   static final KProperty[] $$delegatedProperties = new KProperty[]{(KProperty)Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(Test.class), "test", "getTest()I"))};
   @NotNull
   private final ReadWriteProperty test$delegate;

   public final int getTest() {
      return ((Number)this.test$delegate.getValue(this, $$delegatedProperties[0])).intValue();
   }

   public final void setTest(int var1) {
      this.test$delegate.setValue(this, $$delegatedProperties[0], var1);
   }

   public Test() {
      Delegates var1 = Delegates.INSTANCE;
      Object initialValue$iv = 0;
      int $i$f$observable = false;
      ReadWriteProperty var5 = (ReadWriteProperty)(new Test$$special$$inlined$observable$1(initialValue$iv, initialValue$iv));
      this.test$delegate = var5;
   }
}


//File Test$$special$$inlined$observable$1.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.properties.ObservableProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;

public final class Test$$special$$inlined$observable$1 extends ObservableProperty {
   // $FF: synthetic field
   final Object $initialValue;

   public Test$$special$$inlined$observable$1(Object $captured_local_variable$1, Object $super_call_param$2) {
      super($super_call_param$2);
      this.$initialValue = $captured_local_variable$1;
   }

   protected void afterChange(@NotNull KProperty property, Object oldValue, Object newValue) {
      int var4 = ((Number)newValue).intValue();
      int $noName_1 = ((Number)oldValue).intValue();
      boolean var7 = false;
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// TARGET_BACKEND: JVM
// WITH_RUNTIME
// !LANGUAGE: +NewInference
// ISSUE: KT-32429

import kotlin.properties.Delegates.observable

fun box(): String = "OK"

