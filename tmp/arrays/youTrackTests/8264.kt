// Original bug: KT-18881

data class Node(private val data: MutableList<String>) {
  fun add(word: String) {
    data.add(word)
  }
}

fun foo(node: Node, words: List<String>) {
  for (word in words) {
    node.add(word)
  }
}
