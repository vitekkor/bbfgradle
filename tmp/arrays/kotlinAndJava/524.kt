//File Outer.java
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.NotNull;

public final class Outer {
   @NotNull
   private final String ok = "OK";

   @NotNull
   public final String getOk() {
      return this.ok;
   }

   public final class Inner extends Base {
      public Inner() {
         super((Function0)(new Outer$Inner$1(Outer.this)));
      }
   }
}


//File Outer$Inner$1.java
import kotlin.Metadata;
import kotlin.jvm.internal.PropertyReference0;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import org.jetbrains.annotations.Nullable;

// $FF: synthetic class
final class Outer$Inner$1 extends PropertyReference0 {
   Outer$Inner$1(Outer var1) {
      super(var1);
   }

   public String getName() {
      return "ok";
   }

   public String getSignature() {
      return "getOk()Ljava/lang/String;";
   }

   public KDeclarationContainer getOwner() {
      return Reflection.getOrCreateKotlinClass(Outer.class);
   }

   @Nullable
   public Object get() {
      return ((Outer)this.receiver).getOk();
   }
}


//File Base.java
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public abstract class Base {
   @NotNull
   private final Function0 fn;

   @NotNull
   public final Function0 getFn() {
      return this.fn;
   }

   public Base(@NotNull Function0 fn) {
      super();
      this.fn = fn;
   }
}


//File Main.kt


fun box() = Outer().Inner().fn()

