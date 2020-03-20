//File Delegate.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class Delegate implements Interface {
   @NotNull
   public String greet() {
      return "OK";
   }
}


//File MyObject.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class MyObject implements Interface {
   @NotNull
   private final Interface delegate;

   @NotNull
   public final Interface getDelegate() {
      return this.delegate;
   }

   private MyObject(Interface delegate) {
      this.delegate = delegate;
   }

   public MyObject() {
      this((Interface)(new Delegate()));
   }

   @NotNull
   public String greet() {
      return this.delegate.greet();
   }
}


//File Main.kt


fun box(): String {
    return MyObject().greet()
}



//File Interface.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

interface Interface {
   @NotNull
   String greet();
}
