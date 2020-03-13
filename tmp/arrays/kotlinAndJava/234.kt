//File FirstTrait.java
import kotlin.Metadata;

public interface FirstTrait {
}


//File Foo.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class Foo implements FirstTrait, SecondTrait {
   @NotNull
   public final String bar() {
      return MainKt.doSomething(this);
   }
}


//File SecondTrait.java
import kotlin.Metadata;

public interface SecondTrait {
}


//File Main.kt


fun <T> T.doSomething(): String where T : FirstTrait, T : SecondTrait {
    return "OK"
}

fun box(): String {
    return Foo().bar()
}

