//File DescriptorBasedProperty.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public class DescriptorBasedProperty implements KCallableImpl {
   @NotNull
   public String getReturnType() {
      return KCallableImpl.DefaultImpls.getReturnType(this);
   }
}


//File KMutableProperty1Impl.java
import kotlin.Metadata;

public class KMutableProperty1Impl extends KProperty1Impl implements KCallable {
}


//File KCallableImpl.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface KCallableImpl extends KCallable {
   @NotNull
   String getReturnType();

   public static final class DefaultImpls {
      @NotNull
      public static String getReturnType(KCallableImpl $this) {
         return "OK";
      }
   }
}


//File KCallable.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface KCallable {
   @NotNull
   String getReturnType();
}


//File KProperty1Impl.java
import kotlin.Metadata;

public class KProperty1Impl extends DescriptorBasedProperty implements KCallableImpl2 {
}


//File KCallableImpl2.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface KCallableImpl2 extends KCallableImpl {
   public static final class DefaultImpls {
      @NotNull
      public static String getReturnType(KCallableImpl2 $this) {
         return KCallableImpl.DefaultImpls.getReturnType((KCallableImpl)$this);
      }
   }
}


//File Main.kt


fun box(): String {
    return KMutableProperty1Impl().returnType
}

