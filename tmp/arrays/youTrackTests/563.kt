// Original bug: KT-40948

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

interface Node
open class Parent : Node
class SimpleNode : Node

abstract class Scene { abstract val root: Parent }

@OptIn(ExperimentalContracts::class)
inline fun <T : Node> opcr(parent: Parent, node: T, op: T.() -> Unit = {}): T {
    contract { callsInPlace(op, kotlin.contracts.InvocationKind.EXACTLY_ONCE) }
    return node.apply {
        op(this) /* real invoke, compiler not error, error runtime java.lang.IllegalAccessError */
    }
}

@OptIn(ExperimentalContracts::class)
fun Parent.simpleNode(op: SimpleNode.() -> Unit = {}): SimpleNode {
    contract { callsInPlace(op, kotlin.contracts.InvocationKind.EXACTLY_ONCE) }
    return opcr(this, SimpleNode(), op)
}

@OptIn(ExperimentalContracts::class)
fun Scene.parent(op: Parent.() -> Unit = {}): Parent {
    contract { callsInPlace(op, kotlin.contracts.InvocationKind.EXACTLY_ONCE) }
    return Parent().apply(op)
}

class SimpleScene : Scene() {
    val value: Int
    private val value2: Int

    override val root = parent {
        simpleNode {
            value = 100 // error IllegalAccessError
            value2 = 100 // error IllegalAccessError
        }
    }
}

fun main() {
    val scene = SimpleScene()
    println(scene.value)
}
