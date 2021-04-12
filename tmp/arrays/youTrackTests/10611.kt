// Original bug: KT-3511

fun main(args: Array<String>) {
    var something: Something?
    var somethingElse = Something()
    var somethingNew = Something()

    something = somethingElse
    something?.second = somethingNew.second

}

public class Something() {

    var second: Second = Second()
}

public class Second() {

}
