// Original bug: KT-45436

class Test {
    private fun Class1.myFunction() =
        with(class2) {
            Class3("some-string-constant", myExtProp)
        }

    private val Class2.myExtProp          // <-- Warning shown on this line, on "Class2"
        get() = "myExtProp$bar"
}

data class Class1(val class2: Class2)

data class Class2(val foo: String, val bar: String)

data class Class3(val bar: String, val baz: String)
