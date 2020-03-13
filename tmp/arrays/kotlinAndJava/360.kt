//File Another.java
import kotlin.Metadata;

public final class Another implements Base {
}


//File Base.java
import kotlin.Metadata;

public interface Base {
}


//File Main.kt

operator fun Base.inc(): Derived { return Derived() }

public fun box() : String {
    var i : Base
    i = Another()
    val j = ++i

    return if (j is Derived && i is Derived) "OK" else "fail j = $j i = $i"
}



//File Derived.java
import kotlin.Metadata;

public final class Derived implements Base {
}
