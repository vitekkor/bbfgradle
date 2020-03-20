//File A.java
import kotlin.Metadata;
import kotlin.reflect.KMutableProperty0;
import org.jetbrains.annotations.NotNull;

public final class A {
   @NotNull
   private String value = "fail1";

   @NotNull
   public final String getValue() {
      return this.value;
   }

   // $FF: synthetic method
   public static final String access$getValue$p(A $this) {
      return $this.value;
   }

   // $FF: synthetic method
   public static final void access$setValue$p(A $this, String var1) {
      $this.value = var1;
   }

   public final class B {
      @NotNull
      public final KMutableProperty0 foo() {
         return new A$B$foo$1(A.this);
      }
   }
}


//File C$bar$D$foo$1.java
import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference0;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import org.jetbrains.annotations.Nullable;

// $FF: synthetic class
final class C$bar$D$foo$1 extends MutablePropertyReference0 {
   C$bar$D$foo$1(C var1) {
      super(var1);
   }

   public String getName() {
      return "value";
   }

   public String getSignature() {
      return "getValue()Ljava/lang/String;";
   }

   public KDeclarationContainer getOwner() {
      return Reflection.getOrCreateKotlinClass(C.class);
   }

   @Nullable
   public Object get() {
      return ((C)this.receiver).getValue();
   }

   public void set(@Nullable Object value) {
      C.access$setValue$p((C)this.receiver, (String)value);
   }
}


//File A$B$foo$1.java
import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference0;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import org.jetbrains.annotations.Nullable;

// $FF: synthetic class
final class A$B$foo$1 extends MutablePropertyReference0 {
   A$B$foo$1(A var1) {
      super(var1);
   }

   public String getName() {
      return "value";
   }

   public String getSignature() {
      return "getValue()Ljava/lang/String;";
   }

   public KDeclarationContainer getOwner() {
      return Reflection.getOrCreateKotlinClass(A.class);
   }

   @Nullable
   public Object get() {
      return ((A)this.receiver).getValue();
   }

   public void set(@Nullable Object value) {
      A.access$setValue$p((A)this.receiver, (String)value);
   }
}


//File Main.kt


fun box(): String {
    val a = A()
    a.B().foo().set("O")

    val c = C()
    c.bar().set("K")

    return a.value + c.value
}



//File C.java
import kotlin.Metadata;
import kotlin.reflect.KMutableProperty0;
import org.jetbrains.annotations.NotNull;

public final class C {
   @NotNull
   private String value = "fail2";

   @NotNull
   public final String getValue() {
      return this.value;
   }

   @NotNull
   public final KMutableProperty0 bar() {
      final class D {
         @NotNull
         public final KMutableProperty0 foo() {
            return new C$bar$D$foo$1(C.this);
         }

         public D() {
         }
      }

      return (new D()).foo();
   }

   // $FF: synthetic method
   public static final String access$getValue$p(C $this) {
      return $this.value;
   }

   // $FF: synthetic method
   public static final void access$setValue$p(C $this, String var1) {
      $this.value = var1;
   }
}
