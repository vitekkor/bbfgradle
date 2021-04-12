// Original bug: KT-2980

fun main(args: Array<String>) {
    println(true
    || false
    )// ok

    println((1..2).map{
        true ||
        false
    })//ok

    println((1..2).map{
        true
        ||false
    })//The compiler gives the syntax error "Expecting an element"
}
