//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class A {
   @NotNull
   private final String result;
   @NotNull
   private final ClassDescriptor descriptor;

   @NotNull
   public final String getResult() {
      return this.result;
   }

   @NotNull
   public final ClassDescriptor getDescriptor() {
      return this.descriptor;
   }

   public A(@NotNull ClassDescriptor descriptor) {
      super();
      this.descriptor = descriptor;
      this.result = this.descriptor.getName();
   }
}


//File MemberDescriptor.java
import kotlin.Metadata;

public interface MemberDescriptor extends Named {
}


//File ClassDescriptor.java
import kotlin.Metadata;

public interface ClassDescriptor extends MemberDescriptor, ClassifierDescriptor {
}


//File ClassifierDescriptor.java
import kotlin.Metadata;

public interface ClassifierDescriptor extends Named {
}


//File Main.kt


fun box(): String {
    return A(ClassDescriptorImpl()).result
}



//File Named.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface Named {
   @NotNull
   String getName();
}


//File ClassDescriptorImpl.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class ClassDescriptorImpl implements ClassDescriptor {
   @NotNull
   public String getName() {
      return "OK";
   }
}
