//File FooTrait.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface FooTrait {
   @NotNull
   String getPropertyTest();
}


//File FooDelegate.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class FooDelegate implements FooTrait {
   @NotNull
   private final String propertyTest = "OK";

   @NotNull
   public String getPropertyTest() {
      return this.propertyTest;
   }
}


//File DelegateTest.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class DelegateTest implements FooTrait {
   // $FF: synthetic field
   private final FooDelegate $$delegate_0 = new FooDelegate();

   @NotNull
   public final String test() {
      return this.getPropertyTest();
   }

   @NotNull
   public String getPropertyTest() {
      return this.$$delegate_0.getPropertyTest();
   }
}


//File Main.kt


fun box()  = DelegateTest().test()

