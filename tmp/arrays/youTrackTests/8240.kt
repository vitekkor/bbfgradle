// Original bug: KT-13735


class Foo2 {

    val bar: Pair<String,String>
    val bar2: Pair<String,String>

    init{
        bar = Pair<String,String>("A","B")
        bar2 = Pair<String,String>("A","B")
    }

}