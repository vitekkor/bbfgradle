// Original bug: KT-23692

class Item<T>

fun SomeFunc(items: Array<Item<*>>)
{

}

fun SomeFunc(items: Collection<Item<*>>)// <<< When this function is commented out, it compiles
{

}

fun AnotherFunc()
{
    SomeFunc(arrayOf(Item<Int>(), Item<Int>()))
}
