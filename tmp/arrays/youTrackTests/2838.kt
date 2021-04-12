// Original bug: KT-24376

annotation class DslMark

@DslMark
class A {
    fun funInA() {}
    fun funInAAccessibleToSpecificChild() {}
    fun B.funInAAccessibleToSpecificChild() = A::funInAAccessibleToSpecificChild
    fun funInAAccessibleToChildren() {}
    fun Any.funInAAccessibleToChildren() = A::funInAAccessibleToChildren
}

@DslMark
class B

@DslMark
class C

fun main(args: Array<String>) {
    A().apply inA@{
        B().apply inB@{
            funInAAccessibleToSpecificChild()
            C().apply inC@{
                funInAAccessibleToChildren()
            }
        }
    }
}
