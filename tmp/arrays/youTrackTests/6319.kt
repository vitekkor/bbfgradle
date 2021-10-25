// Original bug: KT-23029

open class Ba

class Wrapping {
    class Nested {
        open class SecondLevelNested
        class NestedExtender : Ba()
    }

    companion object : Nested.SecondLevelNested()
}
