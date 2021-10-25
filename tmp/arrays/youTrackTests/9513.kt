// Original bug: KT-13036

fun foo(a: Int): Int {
    return a
}

fun bar(a: Int): Int {
    return a
}

fun main(args: Array<String>) {

    val f = if (true) ::foo else ::bar                      //compile error
    val f2: (Int) -> Int = if (true) ::foo else ::bar   //compile ok

    val f3 = {        //compile error
        when (1) {
            1 -> ::foo
            else -> ::bar
        }
    }()

    val f4: (Int) -> Int = {        //compile error
        when (1) {
            1 -> ::foo
            else -> ::bar
        }
    }()

    val f5 = {        //compile ok
        when (1) {
            1 -> ::foo as (Int) -> Int
            else -> ::bar as (Int) -> Int
        }
    }()
}
