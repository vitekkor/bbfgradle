// Original bug: KT-5291

public inline fun <T> T.with( f : T.()->Unit ) : T {
  this.f();
  return this
}

public class Cls {
  val string = "Has Value";
  val buffer = StringBuffer().with{
    append(string)
  }
}

public object Obj {
  val string = "Has Value";
  val buffer = StringBuffer().with{
    append(string)
  }
}

fun main(vararg args: String) {
  println("Cls().buffer=\"${Cls().buffer}\"")
  println("Obj.buffer=\"${Obj.buffer}\"")
}
