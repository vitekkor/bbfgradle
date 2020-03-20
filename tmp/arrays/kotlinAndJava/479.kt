//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class A {
   @NotNull
   private final String value;

   @NotNull
   public final String getValue() {
      return this.value;
   }

   public A(@NotNull String value) {
      super();
      this.value = value;
   }

   public final class B {
      @NotNull
      private final String result;
      @NotNull
      private final String s;

      @NotNull
      public final String getResult() {
         return this.result;
      }

      @NotNull
      public final String getS() {
         return this.s;
      }

      public B(@NotNull String s) {
         super();
         this.s = s;
         this.result = A.this.getValue() + "_" + this.s;
      }
   }
}


//File Main.kt


fun box(): String {
    val receiver = C()
    var result = receiver.classReceiver().result
    if (result != "fromC_OK") return "fail 1: $result"

    result = receiver.superReceiver().result
    if (result != "fromC_OK") return "fail 2: $result"


    result = receiver.aReceiver().result
    if (result != "fromA_OK") return "fail 3: $result"

    result = receiver.newAReceiver().result
    if (result != "fromA_OK") return "fail 3: $result"

    result = receiver.extReceiver().result
    if (result != "fromA_OK") return "fail 3: $result"

    return "OK"
}



//File C.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class C extends A {
   @NotNull
   public final A.B classReceiver() {
      return new A.B("OK");
   }

   @NotNull
   public final A.B superReceiver() {
      return new A.B("OK");
   }

   @NotNull
   public final A.B newAReceiver() {
      return new A("fromA").new B("OK");
   }

   @NotNull
   public final A.B aReceiver() {
      A a = new A("fromA");
      return a.new B("OK");
   }

   @NotNull
   public final A.B extReceiver(@NotNull A $this$extReceiver) {
      return $this$extReceiver.new B("OK");
   }

   @NotNull
   public final A.B extReceiver() {
      return this.extReceiver(new A("fromA"));
   }

   public C() {
      super("fromC");
   }
}
