//File ImpulsMigration.java
import kotlin.Metadata;

public final class ImpulsMigration {
   public final void migrate(long oldVersion) {
      long _oldVersion = 1L;
   }
}


//File Main.kt


fun box(): String {
    ImpulsMigration().migrate(1L)
    return "OK"
}

