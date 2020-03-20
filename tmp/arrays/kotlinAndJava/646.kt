//File State.java
import kotlin.Metadata;

public enum State {
   _0,
   _1,
   _2,
   _3;
}


//File Main.kt


fun box() = if(State._0.ordinal == 0 && State._1.ordinal == 1 &&  State._2.ordinal == 2 &&  State._3.ordinal == 3) "OK" else "fail"

