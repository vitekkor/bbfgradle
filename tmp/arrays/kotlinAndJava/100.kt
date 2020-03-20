//File World.java
import java.util.ArrayList;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class World {
   @NotNull
   private final ArrayList items = new ArrayList();
   @NotNull
   private final World.Item foo = new World.Item();

   @NotNull
   public final ArrayList getItems() {
      return this.items;
   }

   @NotNull
   public final World.Item getFoo() {
      return this.foo;
   }

   public final class Item {
      public Item() {
         World.this.getItems().add(this);
      }
   }
}


//File Main.kt


fun box() : String {
  val w = World()
  if (w.items.size != 1) return "fail"
  return "OK"
}

