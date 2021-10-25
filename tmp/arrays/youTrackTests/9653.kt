// Original bug: KT-11175

interface Has1Component<T1> {
    fun component1() : T1
}
interface Has2Components<T1, T2> : Has1Component<T1> {
    fun component2(): T2
}

data class A(val x: String, val y: String) : Has2Components<String, String>
