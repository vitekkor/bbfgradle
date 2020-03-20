//File TestClass.java
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class TestClass {
   @NotNull
   private final Function0 body;

   public final void run() {
      this.body.invoke();
   }

   @NotNull
   public final Function0 getBody() {
      return this.body;
   }

   public TestClass(@NotNull Function0 body) {
      super();
      this.body = body;
   }
}


//File Main.kt


fun box() : String {
    TestClass({}).run()
    return "OK"
}

