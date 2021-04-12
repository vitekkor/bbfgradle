// Original bug: KT-45446

package mine

class NotCompile(val k: Int) : AbstractMutableSet<Int>() {
    companion object {
        const val debug = false
//        const val debug = true


    }

    inner class Node {
        fun remove(v: Int): Boolean {

            if (v != 1) {
                return false
            } else {
                throw RuntimeException()
                var posV = 0

                debug {
                    check(posV == 1)
                }

                return true
            }
        }
    }

    override fun add(v: Int): Boolean {
        return true
    }

    override fun remove(element: Int): Boolean {
        return false
    }

    override fun contains(v: Int): Boolean {

        return false
    }

    override val size: Int
        get() = toList().size


    private fun debug(f: Runnable) {
        if (debug) {
            f.run()
        }
    }

    override fun iterator(): MutableIterator<Int> {
        TODO("Not yet implemented")
    }
}
