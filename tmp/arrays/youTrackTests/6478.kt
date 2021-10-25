// Original bug: KT-28182

package pack

@Target(AnnotationTarget.FIELD)
annotation class Anno

data class C(val x: Int) {
    @Anno
    val json: String = ""
        get() = field

    fun copy() = this.also { json }
}
