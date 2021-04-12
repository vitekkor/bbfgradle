// Original bug: KT-24882

abstract class Node(val name: String) {
    abstract fun toString(namer: Namer): String
}

class Variable(name: String): Node(name) {
    override fun toString(namer: Namer) = namer(this)
}

class Add(name: String, var left: Node, var right: Node): Node(name) {
    override fun toString(namer: Namer) = "(${namer(this)} = ${left.toString(namer)} + ${right.toString(namer)})"
}

class Neg(name: String, var inner: Node): Node(name) {
    override fun toString(namer: Namer) = "(${namer(this)} = -${inner.toString(namer)})"
}

interface Namer {
    operator fun invoke(node: Node): String
}

class CaptureNamer: Namer {
    val nodes = mutableSetOf<Node>()
    override fun invoke(node: Node): String {
        nodes += node
        return "stub"
    }
}

class RealNamer(val map: Map<Node, String>): Namer {
    override fun invoke(node: Node): String = map.getValue(node)
}

fun nodeToString(node: Node): String {
    //first pass
    val capture = CaptureNamer()
    node.toString(capture) //discard actual result "(stub = stub + (stub = -stub))"

    val nodes = capture.nodes

    //choose distinct names, this is not really a correct algorithm but it's close enough
    val names = nodes.groupBy { it.name }.flatMap { (name, nodes) ->
        if (nodes.size > 1)
            nodes.mapIndexed { i, node -> node to name + i }
        else
            listOf(nodes.first() to name)
    }.toMap()

    //second pass
    val namer = RealNamer(names)
    return node.toString(namer)
}

fun main() {
    val a0 = Variable("a")
    val a1 = Variable("a")
    val b = Neg("b", a1)
    val c = Add("c", a0, b)
    println(nodeToString(c)) //prints "(c = a0 + (b = -a1))"
}