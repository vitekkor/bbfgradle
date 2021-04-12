// Original bug: KT-14149

package A

open class SuperClass {

    protected fun myDo() {
        message("OK!")
    }

    open fun message(s: String) {
    }

    companion object {

        @JvmStatic
        var sMessage = "Not OK"

        @JvmStatic
        protected fun myStaticDo() {
            sMessage = "OK!"
        }
    }
}
