// Original bug: KT-40672

package lib

public object Big {
    fun func() {
        object: Abs() {
            override fun some() {
                TODO("Not yet implemented")
            }
        }
    }

    private abstract class Abs {
        protected abstract fun some()
    }
}
