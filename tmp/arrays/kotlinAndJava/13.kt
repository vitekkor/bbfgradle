//File T.java
import kotlin.Metadata;

public interface T {
   void foo();
}


//File D.java
import kotlin.Metadata;

public final class D extends A implements B {
}


//File A.java
import kotlin.Metadata;

public class A implements T {
   public void foo() {
   }
}


//File B.java
import kotlin.Metadata;

public interface B extends T {
}


//File H.java
import kotlin.Metadata;

public final class H extends A implements B, T {
}


//File I.java
import kotlin.Metadata;

public final class I extends A implements T, B {
}


//File J.java
import kotlin.Metadata;

public final class J extends A implements T, B {
}


//File E.java
import kotlin.Metadata;

public final class E extends A implements B, T {
}


//File Main.kt


fun box(): String {
    C().foo()
    D().foo()
    E().foo()
    F().foo()
    G().foo()
    H().foo()
    I().foo()
    J().foo()

    return "OK"
}



//File C.java
import kotlin.Metadata;

public final class C extends A implements B {
}


//File G.java
import kotlin.Metadata;

public final class G extends A implements T, B {
}


//File F.java
import kotlin.Metadata;

public final class F extends A implements B, T {
}
