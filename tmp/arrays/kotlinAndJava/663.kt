//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;

public final class A {
   // $FF: synthetic field
   static final KProperty[] $$delegatedProperties = new KProperty[]{(KProperty)Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(A.class), "result", "getResult()Ljava/lang/String;"))};
   @NotNull
   private final Delegate result$delegate;
   @NotNull
   private final String value;

   @NotNull
   public final String getResult() {
      return this.result$delegate.getValue(this, $$delegatedProperties[0]);
   }

   @NotNull
   public final String getValue() {
      return this.value;
   }

   public A(@NotNull String value) {
      super();
      this.value = value;
      this.result$delegate = (new Delegate("Fail")).provideDelegate(this, $$delegatedProperties[0]);
   }
}


//File Delegate.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class Delegate {
   @NotNull
   private final String value;

   @NotNull
   public final Delegate provideDelegate(@NotNull A instance, @NotNull KProperty property) {
      return new Delegate(instance.getValue());
   }

   @NotNull
   public final String getValue(@Nullable Object instance, @NotNull KProperty property) {
      return this.value;
   }

   @NotNull
   public final String getValue() {
      return this.value;
   }

   public Delegate(@NotNull String value) {
      super();
      this.value = value;
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
import kotlin.reflect.KProperty

fun box(): String {
    return A("OK").result
}

