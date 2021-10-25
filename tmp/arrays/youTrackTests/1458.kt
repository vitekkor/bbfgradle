// Original bug: KT-16084

class Test {

    fun test() {

        let {
            noInlineFun {
                noInlineFun { }
            }
        }
    }

    private fun noInlineFun(lambda: () -> Unit) = lambda()

}
