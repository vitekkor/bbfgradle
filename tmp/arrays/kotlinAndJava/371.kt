//File Delegate.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class Delegate {
   @NotNull
   public final String getValue(@Nullable Object t, @NotNull KProperty p) {
      return "OK";
   }

   public final void setValue(@Nullable Object t, @NotNull KProperty p, @NotNull String i) {
      }
}


//File MyClass.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;

public final class MyClass {
   // $FF: synthetic field
   static final KProperty[] $$delegatedProperties = new KProperty[]{(KProperty)Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(MyClass.class), "x", "getX()Ljava/lang/String;"))};
   @NotNull
   private final Delegate x$delegate = new Delegate();

   @NotNull
   public final String getX() {
      return this.x$delegate.getValue(this, $$delegatedProperties[0]);
   }

   @First
   public final void setX(@NotNull String var1) {
      this.x$delegate.setValue(this, $$delegatedProperties[0], var1);
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// TARGET_BACKEND: JVM

// WITH_RUNTIME

import kotlin.reflect.KProperty

fun box(): String {
    val e = MyClass::class.java

    val e1 = e.getDeclaredMethod("setX", String::class.java).getAnnotations()
    if (e1.size != 1) return "Fail E1 size: ${e1.toList()}"
    if (e1[0].annotationClass.java != First::class.java) return "Fail: ${e1.toList()}"

    return MyClass().x
}



//File First.java
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.Retention;

@Retention(AnnotationRetention.RUNTIME)
@java.lang.annotation.Retention(RetentionPolicy.RUNTIME)
public @interface First {
}
