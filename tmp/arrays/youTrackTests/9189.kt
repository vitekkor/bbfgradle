// Original bug: KT-11734

class Foo() {
    companion object {
        private const val PRIVATE_CONST = ""
        const val CONST = ""
    }
    
    fun testPrivateConst() =
            PRIVATE_CONST   // INVOKESTATIC Foo.access$getPRIVATE_CONST$cp ()Ljava/lang/String;
                // NB unnecessary accessor generated

    fun testConst() =
            CONST           // GETSTATIC Foo.CONST : Ljava/lang/String;
}
