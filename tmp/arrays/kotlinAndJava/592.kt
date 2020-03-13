//File Base.java
import java.util.HashSet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class Base extends HashSet {
   public boolean remove(@NotNull DatabaseEntity element) {
      return true;
   }
}


//File DatabaseEntity.java
import kotlin.Metadata;

public class DatabaseEntity {
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// KJS_WITH_FULL_RUNTIME
// IGNORE_BACKEND: NATIVE

fun box(): String {
    val sprintIssues = Derived()
    if (!sprintIssues.remove(Issue())) return "Fail"

    return "OK"
}



//File Issue.java
import kotlin.Metadata;

public final class Issue extends DatabaseEntity {
}


//File Derived.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class Derived extends Base {
   public boolean remove(@NotNull Issue element) {
      return super.remove((DatabaseEntity)element);
   }
}
