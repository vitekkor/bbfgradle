//File T.java
import kotlin.Metadata;

public interface T {
   void foo();
}


//File D.java
import kotlin.Metadata;

public final class D extends A implements U {
}


//File A.java
import kotlin.Metadata;

public class A implements T {
   public void foo() {
   }
}


//File B.java
import kotlin.Metadata;

public final class B extends A implements T {
}


//File H.java
import kotlin.Metadata;

public final class H extends A implements U, T {
}


//File I.java
import kotlin.Metadata;

public final class I extends A implements T, U {
}


//File J.java
import kotlin.Metadata;

public final class J extends A implements U, T {
}


//File E.java
import kotlin.Metadata;

public final class E extends A implements U {
}


//File U.java
import kotlin.Metadata;

public interface U extends T {
}


//File K.java
import kotlin.Metadata;

public final class K extends A implements T, U {
}


//File Main.kt


fun box(): String {
    B().foo()
    C().foo()
    D().foo()
    E().foo()
    F().foo()
    G().foo()
    H().foo()
    I().foo()
    J().foo()
    K().foo()

    return "OK"
}



//File C.java
import kotlin.Metadata;

public final class C extends A implements T {
}


//File G.java
import kotlin.Metadata;

public final class G extends A implements T, U {
}


//File F.java
import kotlin.Metadata;

public final class F extends A implements U, T {
}
