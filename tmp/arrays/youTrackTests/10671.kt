// Original bug: KT-2004

fun foo() : Collection<String>{
  return list()
}

public fun <T> list(vararg items : T) : List<T> {
  val list = ArrayList<T>(items.size)
  for (i in 0..items.size - 1){
    list.add(items[i])
  }
  return list
}
