// Original bug: KT-38085

fun main() {
    println(Bar(foo = "initial foo").property)
}

class Bar(private val foo: String) { // Constructor parameter is never used as a property 
    val property = foo.myDsl {
        "DSL-local foo: $foo"
    }
}

class MyBuilder(foo: String) {
    val foo = "builder-local foo: $foo"
}

fun String.myDsl(init: MyBuilder.() -> String) =
    MyBuilder(this).run(init)
