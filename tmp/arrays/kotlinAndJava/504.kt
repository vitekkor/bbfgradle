//File Caller.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

public final class Caller {
   private final Sample member;

   public final void test() {
      Sample var10000 = this.member;
      if (var10000 == null) {
         }

      var10000.getCallMe();
   }

   public final Sample getMember() {
      return this.member;
   }

   public Caller(Sample member) {
      this.member = member;
   }
}


//File Sample.java
import kotlin.Metadata;

public interface Sample {
   int getCallMe();
}


//File Main.kt


fun box(): String {
    return "OK"
}

