//File DeeperBase.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public class DeeperBase {
   @NotNull
   public String deeperBaseFun() {
      return "DeeperBase.deeperBaseFun()";
   }

   @NotNull
   public String getDeeperBaseProp() {
      return "DeeperBase.deeperBaseProp";
   }
}


//File DeepBase.java
import kotlin.Metadata;

public class DeepBase extends DeeperBase {
}


//File DeepDerived.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class DeepDerived extends DeepBase implements DeepInterface {
   @NotNull
   public String deeperBaseFun() {
      return "DeepDerived.deeperBaseFun()";
   }

   @NotNull
   public String getDeeperBaseProp() {
      return "DeepDerived.deeperBaseProp";
   }

   @NotNull
   public String deeperInterfaceFun() {
      return "DeepDerived.deeperInterfaceFun()";
   }

   @NotNull
   public String getDeeperInterfaceProp() {
      return "DeepDerived.deeperInterfaceProp";
   }

   @NotNull
   public String deepInterfaceFun() {
      return "DeepDerived.deepInterfaceFun()";
   }

   @NotNull
   public final String callsSuperDeeperBaseFun() {
      return super.deeperBaseFun();
   }

   @NotNull
   public final String getsSuperDeeperBaseProp() {
      return super.getDeeperBaseProp();
   }

   @NotNull
   public final String callsSuperDeepInterfaceFun() {
      return DeepInterface.DefaultImpls.deepInterfaceFun(this);
   }

   @NotNull
   public final String callsSuperDeeperInterfaceFun() {
      return DeepInterface.DefaultImpls.deeperInterfaceFun(this);
   }

   @NotNull
   public final String getsSuperDeeperInterfaceProp() {
      return DeepInterface.DefaultImpls.getDeeperInterfaceProp(this);
   }
}


//File Main.kt


fun box(): String {
    val dd = DeepDerived()

    val test1 = dd.callsSuperDeeperBaseFun()
    if (test1 != "DeeperBase.deeperBaseFun()") return "Failed: dd.callsSuperDeeperBaseFun()==$test1"

    val test2 = dd.getsSuperDeeperBaseProp()
    if (test2 != "DeeperBase.deeperBaseProp") return "Failed: dd.getsSuperDeeperBaseProp()==$test2"

    val test3 = dd.callsSuperDeepInterfaceFun()
    if (test3 != "DeepInterface.deepInterfaceFun()") return "Failed: dd.callsSuperDeepInterfaceFun()==$test3"

    val test4 = dd.callsSuperDeeperInterfaceFun()
    if (test4 != "DeeperInterface.deeperInterfaceFun()") return "Failed: dd.callsSuperDeeperInterfaceFun()==$test4"

    val test5 = dd.getsSuperDeeperInterfaceProp()
    if (test5 != "DeeperInterface.deeperInterfaceProp") return "Failed: dd.getsSuperDeeperInterfaceProp()==$test5"

    return "OK"
}



//File DeepInterface.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface DeepInterface extends DeeperInterface {
   @NotNull
   String deepInterfaceFun();

   public static final class DefaultImpls {
      @NotNull
      public static String deepInterfaceFun(DeepInterface $this) {
         return "DeepInterface.deepInterfaceFun()";
      }

      @NotNull
      public static String deeperInterfaceFun(DeepInterface $this) {
         return DeeperInterface.DefaultImpls.deeperInterfaceFun((DeeperInterface)$this);
      }

      @NotNull
      public static String getDeeperInterfaceProp(DeepInterface $this) {
         return DeeperInterface.DefaultImpls.getDeeperInterfaceProp((DeeperInterface)$this);
      }
   }
}


//File DeeperInterface.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface DeeperInterface {
   @NotNull
   String deeperInterfaceFun();

   @NotNull
   String getDeeperInterfaceProp();

   public static final class DefaultImpls {
      @NotNull
      public static String deeperInterfaceFun(DeeperInterface $this) {
         return "DeeperInterface.deeperInterfaceFun()";
      }

      @NotNull
      public static String getDeeperInterfaceProp(DeeperInterface $this) {
         return "DeeperInterface.deeperInterfaceProp";
      }
   }
}
