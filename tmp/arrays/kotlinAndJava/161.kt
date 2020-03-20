//File Annotation.java
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;
import kotlin.annotation.Retention;
import kotlin.annotation.Target;

@Target(
   allowedTargets = {AnnotationTarget.EXPRESSION}
)
@Retention(AnnotationRetention.SOURCE)
@java.lang.annotation.Retention(RetentionPolicy.SOURCE)
@java.lang.annotation.Target({})
public @interface Annotation {
}


//File Main.kt


fun box(): String {
    var v = 0
    @Annotation v += 1 + 2
    if (v != 3) return "fail1"

    @Annotation v = 4
    if (v != 4) return "fail2"

    return "OK"
}

