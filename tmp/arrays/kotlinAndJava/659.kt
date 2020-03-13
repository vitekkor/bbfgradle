//File Exception1.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class Exception1 extends Exception {
   public Exception1(@NotNull String msg) {
      super(msg);
   }
}


//File Exception2.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class Exception2 extends Exception {
   public Exception2(@NotNull String msg) {
      super(msg);
   }
}


//File Exception3.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class Exception3 extends Exception {
   public Exception3(@NotNull String msg) {
      super(msg);
   }
}


//File Main.kt


fun box(): String =
        "O" + try {
            throw Exception3("K")
        }
        catch (e1: Exception1) {
            "e1"
        }
        catch (e2: Exception2) {
            "e2"
        }
        catch (e3: Exception3) {
            e3.message
        }
        catch (e: Exception) {
            "e"
        }

