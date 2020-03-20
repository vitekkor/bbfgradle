//File Delegate.java
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

public final class Delegate {
   public static final Delegate INSTANCE;

   public final boolean getValue(@Nullable Object thiz, @Nullable Object metadata) {
      return true;
   }

   public final void setValue(@Nullable Object thiz, @Nullable Object metadata, boolean value) {
   }

   private Delegate() {
   }

   static {
      Delegate var0 = new Delegate();
      INSTANCE = var0;
   }
}


//File TestIt.java
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;

public final class TestIt {
   // $FF: synthetic field
   static final KProperty[] $$delegatedProperties = new KProperty[]{(KProperty)Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(TestIt.class), "isUpdateable", "getIsUpdateable()Z"))};
   private boolean isFries = true;
   @NotNull
   private final Delegate isUpdateable$delegate;

   @JvmName(
      name = "getIsFries"
   )
   public final boolean getIsFries() {
      return this.isFries;
   }

   @JvmName(
      name = "setIsFries"
   )
   public final void setIsFries(boolean var1) {
      this.isFries = var1;
   }

   @JvmName(
      name = "getIsUpdateable"
   )
   public final boolean getIsUpdateable() {
      return this.isUpdateable$delegate.getValue(this, $$delegatedProperties[0]);
   }

   @JvmName(
      name = "setIsUpdateable"
   )
   public final void setIsUpdateable(boolean var1) {
      this.isUpdateable$delegate.setValue(this, $$delegatedProperties[0], var1);
   }

   public TestIt() {
      this.isUpdateable$delegate = Delegate.INSTANCE;
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// TARGET_BACKEND: JVM

// WITH_RUNTIME

import kotlin.test.assertEquals

fun box(): String {
    assertEquals(
            listOf("getIsFries", "getIsUpdateable", "setIsFries", "setIsUpdateable"),
            TestIt::class.java.declaredMethods.map { it.name }.sorted()
    )

    return "OK"
}

