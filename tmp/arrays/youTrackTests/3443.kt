// Original bug: KT-38496

package test2

class B {

    @Deprecated("test", ReplaceWith(
        "this.testFunB().testFun(test3.TestObject)",
        "test1.testFun",
        "test3.TestObject"
    ))
    fun b(contains: String) {

    }
}
