// Original bug: KT-44703

interface RandomGenerator {

    companion object {

        val default: DefaultGenerator by lazy(::DefaultGenerator)

    }
}

inline class DefaultGenerator(val random: Int = 5) : RandomGenerator
