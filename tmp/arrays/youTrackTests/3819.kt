// Original bug: KT-16583

private const val DURATION = 250L
class Foo {
    var bar: Long = 0

    constructor(value: Long) {
        /** ... **/
    }

    init {
        bar = DURATION
    }
}
