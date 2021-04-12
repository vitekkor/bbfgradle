// Original bug: KT-11306

interface MustHaveEquals {
    override fun equals(other: Any?): Boolean
}

data class FooClass(val num: Int) : MustHaveEquals  // ABSTRACT_MEMBER_NOT_IMPLEMENTED
