// Original bug: KT-2811

open class Test1 {
    fun report() =
            if (this is Test2)
                "Test2(${this.value})"
            else
                "Test1()"
}

class Test2(val value: Int): Test1()

fun main(args: Array<String>) {
    val test = Test2(10).report()
    // throws Exception in thread "main" java.lang.VerifyError: (class: Test1, method: report signature: ()Ljava/lang/String;)
    //   Incompatible object argument for function call
}
