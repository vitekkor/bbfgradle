// Original bug: KT-44233

import java.util.concurrent.CopyOnWriteArrayList
  
class StringIterable : Iterable<String> {
  private val strings = CopyOnWriteArrayList<String>()
  override fun iterator() = strings.iterator()
}
