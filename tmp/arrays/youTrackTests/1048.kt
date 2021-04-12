// Original bug: KT-13108

class T
class U

interface BehaviourT {
    fun handle(message: T)
}

interface BehaviourU {
    fun handle(message: U)
}

class Actor : BehaviourT, BehaviourU {
    override fun handle(message: T) {
        TODO("Handle Behaviour<T>")
    }

    override fun handle(message: U) {
        TODO("Handle Behaviour<U>")
    }
}
