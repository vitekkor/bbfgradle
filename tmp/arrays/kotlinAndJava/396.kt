//File A.java
import kotlin.Metadata;

public class A {
   public class InnerA {
   }
}


//File B.java
import kotlin.Metadata;

public final class B extends A {
   public final class InnerB extends A.InnerA {
      public InnerB() {
         super();
      }
   }
}


//File Main.kt


fun box(): String {
    B().InnerB()
    return "OK"
}

