//File IWithToString.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface IWithToString {
   @NotNull
   String toString();
}


//File ISomething.java
import kotlin.Metadata;

public interface ISomething {
}


//File C1.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class C1 extends ClassWithToString implements ISomething {
   @NotNull
   public String toString() {
      return super.toString();
   }
}


//File ClassWithToString.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public class ClassWithToString {
   @NotNull
   public String toString() {
      return "C";
   }
}


//File Main.kt


fun box(): String {
    return when {
        C1().toString() != "C" -> "Failed #1"
        C2().toString() != "C" -> "Failed #2"
        else -> "OK"
    }
}



//File C2.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class C2 extends ClassWithToString implements IWithToString, ISomething {
   @NotNull
   public String toString() {
      return super.toString();
   }
}
