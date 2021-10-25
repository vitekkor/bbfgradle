// Original bug: KT-20569

const val VERIFY_ASSUMPTIONS = false //deploying to production

fun generateValueInBounds(lowerBound: Int, upperBound: Int): Int {
    assumeTrue(lowerBound <= upperBound)
    return lowerBound //fake implementation for demo
}

inline fun assumeTrue(value: Boolean) {
    if (VERIFY_ASSUMPTIONS) {
        assert(value)
    }
}
