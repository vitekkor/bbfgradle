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

public class KMutableProperty1Impl extends KProperty1Impl implements KMutableProperty1, KMutablePropertyImpl {
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


//File KMutableProperty1.java
import kotlin.Metadata;

public interface KMutableProperty1 extends KProperty1, KMutableProperty {
}


//File KProperty1.java
import kotlin.Metadata;

public interface KProperty1 extends KProperty {
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

public class KProperty1Impl extends DescriptorBasedProperty implements KProperty1, KPropertyImpl {
}


//File Main.kt


fun box(): String {
    return KMutableProperty1Impl().returnType
}



//File KMutableProperty.java
import kotlin.Metadata;

public interface KMutableProperty extends KProperty {
}


//File KPropertyImpl.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface KPropertyImpl extends KProperty, KCallableImpl {
   public static final class DefaultImpls {
      @NotNull
      public static String getReturnType(KPropertyImpl $this) {
         return KCallableImpl.DefaultImpls.getReturnType((KCallableImpl)$this);
      }
   }
}


//File KProperty.java
import kotlin.Metadata;

public interface KProperty extends KCallable {
}


//File KMutablePropertyImpl.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface KMutablePropertyImpl extends KPropertyImpl {
   public static final class DefaultImpls {
      @NotNull
      public static String getReturnType(KMutablePropertyImpl $this) {
         return KPropertyImpl.DefaultImpls.getReturnType((KPropertyImpl)$this);
      }
   }
}
