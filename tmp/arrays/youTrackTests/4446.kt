// Original bug: KT-26038

sealed class Node {

    data class ValueNode(val value: Any, val tail: Node) : Node()

    data class EndNode(val dummy: Unit = Unit): Node()
}

class NodeHolder(val node: Node) {

    // compiler complains that the fun is not tail recursive
    tailrec fun recurseOnNode(): Unit {
        return when (node) {
            is Node.EndNode -> {
                System.out.println("end")
            }
            is Node.ValueNode -> {
                System.out.println(node.value)
                NodeHolder(node.tail).recurseOnNode()
            }
        }
    }
}

// compiler is happy
tailrec fun NodeHolder.recurseOnNodeExt(): Unit {
    return when (node) {
        is Node.EndNode -> {
            System.out.println("end")
        }
        is Node.ValueNode -> {
            System.out.println(node.value)
            NodeHolder(node.tail).recurseOnNodeExt()
        }
    }
}
