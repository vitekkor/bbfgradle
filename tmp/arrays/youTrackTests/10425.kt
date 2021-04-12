// Original bug: KT-5853

public class LinkedList {
    private class Node(var nodeValue: Int, var next: Node? = null)
    private var head: Node? = null

    fun insert(n: Int) {
        head?.next = Node(n)
    }
}
