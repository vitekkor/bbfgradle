//File Point.java
import kotlin.Metadata;

public final class Point {
   private final int x;
   private final int y;

   public final int getX() {
      return this.x;
   }

   public final int getY() {
      return this.y;
   }

   public Point(int x, int y) {
      this.x = x;
      this.y = y;
   }
}


//File Main.kt


fun box() : String {
    val answer = apply(Point(3, 5), { scalar : Int ->
        Point(x * scalar, y * scalar)
    })

    return if (answer.x == 6 && answer.y == 10) "OK" else "FAIL"
}

fun apply(arg:Point, f :  Point.(scalar : Int) -> Point) : Point {
    return arg.f(2)
}

