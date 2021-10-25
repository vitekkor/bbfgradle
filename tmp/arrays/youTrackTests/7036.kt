// Original bug: KT-26384

class Test {
    fun test() {
        try {
            apply {
                try {
                    return test()
                } catch (e: Exception) {

                } catch (e: Throwable) {

                }
            }
        } finally {
            
        }
    }
}
