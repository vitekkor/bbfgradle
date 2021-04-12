// Original bug: KT-2881

public fun<K, V> MutableMap<K, V>.add(key : K, value : V){
  val prev = this.put(key, value)
  if (prev != null){
    throw IllegalArgumentException("There is already an item by key $key")
  }
}
