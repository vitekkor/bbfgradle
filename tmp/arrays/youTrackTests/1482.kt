// Original bug: KT-20542

package pkg1

internal class Internals {
    private fun pvt(limit: Int = 0) {
    }

    internal inline fun failing() {
        pvt()
    }

    internal inline fun working() {
        pvt(0)
    }
}
