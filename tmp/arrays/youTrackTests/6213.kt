// Original bug: KT-29486

class MyController {


    fun test1( test1: String) {
        bar()
    }

    private fun bar() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
