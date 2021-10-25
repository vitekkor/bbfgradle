// Original bug: KT-19609

import java.util.function.Predicate

class TestSamCtor(private val predicate: Predicate<String>) {

    operator fun not(): TestSamCtor {
        return TestSamCtor({ s -> !predicate.test(s) }) // (*)
    }
}

