// Original bug: KT-26458

class TestInlineCallBack {

    fun setBreakpoint(peer: String?, params: String?): String {
        //Looks like this method should not be called at all.
        val action: () -> String = {
            throw IllegalArgumentException("Unexpected Debugger.setBreakpoint() is called by Chrome DevTools: " + params)
        }
        return runStethoSafely(action)
    }

    inline private fun <reified T> runStethoSafely(action: () -> T): T {
        try {
            return action()
        } catch (e: Exception) {
            // T::class is used in different way in production code here
            return T::class.java.newInstance()
        }
    }
}
