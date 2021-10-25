// Original bug: KT-23093

import kotlin.concurrent.thread

class Vertex { val conversions: MutableList<Edge> = mutableListOf() }
class Edge(val vertex: Vertex)
fun addEdge(edge: Edge) { edge.vertex.conversions.add(edge) }

fun fail(vertex: Vertex) {
    vertex.conversions.forEach { edge -> // Either ConcurrentModificationException here.
        val nextVertex = edge.vertex // Or NullPointerException here.
    }
}

fun main(args: Array<String>) {
    val vertex = Vertex()
    (1..50).forEach {
        thread {
            (1..50).forEach {
                addEdge(Edge(vertex))
                fail(vertex)
            }
        }
    }
}
