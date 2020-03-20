//File Foo.java
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;

@Retention(RetentionPolicy.RUNTIME)
public @interface Foo {
   @Retention(RetentionPolicy.RUNTIME)
   public @interface Bar {
   }
}


//File Main.kt


@Foo.Bar
fun box(): String {
    return "OK"
}

