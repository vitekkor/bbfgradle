// Original bug: KT-27731

data class Foo(var bar: String)

@Deprecated("", ReplaceWith("this.function(value)"))
fun Foo.replaceFunctionWithFunction(value: Boolean): Unit = TODO()

@Deprecated("", ReplaceWith("this.property = value"))
fun Foo.replaceFunctionWithProperty(value: Boolean): Unit = TODO()

@Deprecated("", ReplaceWith("this.property"))
var Foo.replacePropertyWithProperty: Boolean
    get() = TODO()
    set(value) = TODO()

@Deprecated("", ReplaceWith("this.function(value)"))
var Foo.replacePropertyWithFunction: Boolean
    get() = TODO()
    set(value) = TODO()

fun Foo.function(value: Boolean): Unit = TODO()

var Foo.property: Boolean
    get() = TODO()
    set(value) = TODO()
