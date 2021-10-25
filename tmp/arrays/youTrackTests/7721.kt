// Original bug: KT-26039

class C {
    companion object {
        fun SomeFunction()
        {}
    }
}

typealias CAlias = C
//        ^^^^^^ Type alias "CAlias" is never used

fun F()
{
    CAlias.SomeFunction()
}
