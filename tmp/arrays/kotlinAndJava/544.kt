//File PhysicalVirtualFile.java
import kotlin.Metadata;

public final class PhysicalVirtualFile extends VirtualFile {
   public long getSize() {
      return 11L;
   }
}


//File VirtualFile.java
import kotlin.Metadata;

public abstract class VirtualFile {
   public abstract long getSize();
}


//File Main.kt


fun box() : String {
    PhysicalVirtualFile()
    return "OK"
}

