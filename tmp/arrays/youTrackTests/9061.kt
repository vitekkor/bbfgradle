// Original bug: KT-16555

open class LockFreeLinkedListNode
private class SendBuffered : LockFreeLinkedListNode()
open class AddLastDesc2<out T : LockFreeLinkedListNode>(node: T)
typealias AddLastDesc<T> = AddLastDesc2<T>

fun describeSendBuffered(): AddLastDesc<*> {
    return object : AddLastDesc<SendBuffered>(SendBuffered()) {}
}

fun main(args: Array<String>) {
    describeSendBuffered()
}

