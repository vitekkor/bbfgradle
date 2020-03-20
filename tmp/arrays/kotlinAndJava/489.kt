//File SecondOwner.java
import kotlin.Metadata;

public interface SecondOwner {
}


//File A.java
import kotlin.Metadata;

public final class A implements FirstOwner {
}


//File B.java
import kotlin.Metadata;

public final class B implements SecondOwner {
}


//File Holder.java
import kotlin.Metadata;

public interface Holder extends StubElement {
}


//File FirstOwner.java
import kotlin.Metadata;

public interface FirstOwner extends SecondOwner {
}


//File Main.kt


fun test(a: A?, b: B) {
    val c = a ?: b
}

fun box(): String {
    test(A(), B())
    return "OK"
}



//File StubElement.java
import kotlin.Metadata;

public interface StubElement {
}
