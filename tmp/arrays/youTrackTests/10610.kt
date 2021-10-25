// Original bug: KT-3513

fun main(args: Array<String>) {
    var something: Something?
    var somethingElse = Something()

    println("test")

    something = somethingElse
// the line below cause the compiler exception
    something?.second = somethingElse.second

}

public class Something() {

    var second: Second = Second()
}

public class Second() {

}
