// Original bug: KT-24425

interface I { val name: String }
class A : I {
   companion object {
        const val name = "man"
    }
    override val name = Companion.name //state we can remove name but we cannot because the compiler runs into a recursive problem
}
