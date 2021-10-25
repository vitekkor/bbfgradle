// Original bug: KT-38627

package cccc.ssss

class SomeVeryImportantClass {
    fun getSomeData(): Any? = null

    companion object {
        fun getInstance(o: Any): SomeVeryImportantClass = SomeVeryImportantClass()
    }
}
