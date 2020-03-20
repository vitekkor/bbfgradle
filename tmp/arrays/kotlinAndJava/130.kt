//File My.java
import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.Delegates;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;

public final class My {
   // $FF: synthetic field
   static final KProperty[] $$delegatedProperties = new KProperty[]{(KProperty)Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(My.class), "delegate", "getDelegate()Ljava/lang/String;"))};
   @NotNull
   private final ReadWriteProperty delegate$delegate;

   @NotNull
   public final String getDelegate() {
      return (String)this.delegate$delegate.getValue(this, $$delegatedProperties[0]);
   }

   private final void setDelegate(String var1) {
      this.delegate$delegate.setValue(this, $$delegatedProperties[0], var1);
   }

   public My() {
      this.delegate$delegate = Delegates.INSTANCE.notNull();
      this.setDelegate("OK");
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// KJS_WITH_FULL_RUNTIME
// WITH_RUNTIME
// See KT-10107: 'Variable must be initialized' for delegate with private set

fun box() = My().delegate

