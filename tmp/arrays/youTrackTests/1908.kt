// Original bug: KT-43437

object Test1 {
    fun call(r: () -> Unit) {}

    object Scope {
        fun call(r: suspend () -> Unit) {}

        fun bar(f: () -> Unit) {
            call(f) //[COMPATIBILITY_WARNING] Candidate resolution will be changed soon, please use fully qualified name to invoke the following closer candidate explicitly: public final fun call(r: suspend () â Unit): Unit defined in check.replacement.Test1.Scope
        }
    }
}
