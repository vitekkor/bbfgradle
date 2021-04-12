// Original bug: KT-18404

import kotlin.reflect.KClass
import kotlin.reflect.KParameter

fun main(args: Array<String>) {
  val manyProperties32 = createInstance(ManyProperties32::class)
  println(manyProperties32.v01)
  println(manyProperties32.v32)

  val manyProperties33 = createInstance(ManyProperties33::class)
  println(manyProperties33.v01)
  println(manyProperties33.v33)
}

fun <T: Any> createInstance(type: KClass<T>) : T {
  val constructor = type.constructors.first()
  var nextArgument: Int = 101
  val map = mutableMapOf<KParameter, Any>()
  for (parameter in constructor.parameters) {
    map[parameter] = nextArgument++
  }
  return constructor.callBy(map)
}

class ManyProperties32(
    var v01: Int, var v02: Int, var v03: Int, var v04: Int, var v05: Int,
    var v06: Int, var v07: Int, var v08: Int, var v09: Int, var v10: Int,
    var v11: Int, var v12: Int, var v13: Int, var v14: Int, var v15: Int,
    var v16: Int, var v17: Int, var v18: Int, var v19: Int, var v20: Int,
    var v21: Int, var v22: Int, var v23: Int, var v24: Int, var v25: Int,
    var v26: Int, var v27: Int, var v28: Int, var v29: Int, var v30: Int,
    var v31: Int, var v32: Int)

class ManyProperties33(
    var v01: Int, var v02: Int, var v03: Int, var v04: Int, var v05: Int,
    var v06: Int, var v07: Int, var v08: Int, var v09: Int, var v10: Int,
    var v11: Int, var v12: Int, var v13: Int, var v14: Int, var v15: Int,
    var v16: Int, var v17: Int, var v18: Int, var v19: Int, var v20: Int,
    var v21: Int, var v22: Int, var v23: Int, var v24: Int, var v25: Int,
    var v26: Int, var v27: Int, var v28: Int, var v29: Int, var v30: Int,
    var v31: Int, var v32: Int, var v33: Int)
