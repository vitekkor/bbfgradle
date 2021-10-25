// Original bug: KT-12191

interface Digraph<T> { val node: Int }
interface AcyclicDigraph<T> : Digraph<T> { }
fun <T> Digraph<T>.assertAcyclic(): AcyclicDigraph<T> = AcyclicDigraphImpl(this)
class AcyclicDigraphImpl<T>(val graph: Digraph<T>) : AcyclicDigraph<T>, Digraph<T> by graph {
	init {
		// @TODO: check this is acyclic!
	}
}
