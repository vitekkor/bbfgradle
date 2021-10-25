// Original bug: KT-36737

internal interface Foo<T> {
    fun bar(fuzz: T)
}

internal abstract class Barg : Foo<String?>
internal class Snarg : Barg() {
    fun <T> stuff(buzz: Barg) {
        buzz.bar(null)
    }

    override fun bar(fuzz: String?) {
        println(fuzz!!.length)
    }
}
