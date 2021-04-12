// Original bug: KT-16940

annotation class TestAnn(val value: Int) {
    companion object { 

        val ZERO = 0
        val ONE = 1
    }
}
// Error:(3, 42) Kotlin: Body is not allowed for annotation class
// Error:(4, 5) Kotlin: Modifier 'companion' is not applicable inside 'annotation class'
