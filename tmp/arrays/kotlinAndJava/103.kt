//File Test.java
import kotlin.Metadata;

public final class Test {
   public interface Foo {
   }

   public static final class FooImplNested implements Test.Foo {
   }

   public final class FooImplInner implements Test.Foo {
   }
}


//File Main.kt


fun box(): String {
    Test().FooImplInner()
    Test.FooImplNested()
    return "OK"
}

