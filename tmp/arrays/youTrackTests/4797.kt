// Original bug: KT-34673

class Foo {
    private var someInt: Int? = null

    //some comment 1
//some comment 2
    var state: Int?
        get() = someInt
        set(state) {
            //some comment 1
            someInt = state
//some comment 2
            if (state == 2) println("2")
        }
}
