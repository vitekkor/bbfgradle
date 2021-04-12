// Original bug: KT-34654

class Foo {
    //some comment 1
    var someInt = 1
        set(state) {
            field = state
            //some comment 1
            println("Some text")
        }

}
