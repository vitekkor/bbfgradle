// Original bug: KT-25897

interface Foo {
    fun add(i: Int): Boolean 
}
class Bar: ArrayList<Int>(), Foo {
    override fun add(i: Int) /* PARAMETER_NAME_CHANGED_ON_OVERRIDE */ = 
        super.add(i)
}
