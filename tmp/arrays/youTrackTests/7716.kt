// Original bug: KT-25147

// common module
class Regular {
    fun doSomething1(flag: Boolean) {
        println("The result is: 42") // conditional breakpoint: `flag == true`
    }
}
