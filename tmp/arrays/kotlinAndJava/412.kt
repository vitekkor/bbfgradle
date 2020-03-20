//File It.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class It {
   @NotNull
   private final String id;

   @NotNull
   public final String getId() {
      return this.id;
   }

   public It(@NotNull String id) {
      super();
      this.id = id;
   }
}


//File Main.kt


fun box(): String {
    val projectId = "projectId"
    val it = It("it")


    fun selectMetaRunnerId(): String {
        operator fun Int?.inc() = (this ?: 0) + 1
        var counter: Int? = null
        fun path(metaRunnerId: String) = counter != 2

        var i = 0
        while (true) {
            val name = projectId + "_" + it.id + (if (counter == null) "" else "_$counter")
            if (!path(name)) {
                return name
            }
            counter++

            i++
            if (i > 2) return "Infinity loop: $counter"
        }
    }
    val X = selectMetaRunnerId()
    if (X != projectId + "_" + it.id + "_2") return "fail: $X"
    return "OK"
}

