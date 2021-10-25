// Original bug: KT-6653

class TestException: RuntimeException() {
    //no compile error:
    override val message: String = ""

    //compile error:
    /*override fun getMessage(): String
    {
        return ""
    }*/

    //compile error:
    //override val localizedMessage: String = ""

    //no compile error:
    override fun getLocalizedMessage(): String
    {
        return ""
    }
}
