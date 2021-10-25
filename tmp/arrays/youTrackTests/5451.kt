// Original bug: KT-18634

fun nonDefaultParameter(paramFun: (Int) -> Int) {
    // SMART_STEP_INTO_BY_INDEX: 0
    // RESUME
    //Breakpoint!
    paramFun(12)
}

fun main(args: Array<String>) {
    nonDefaultParameter(::test)
}

fun test(i: Int) : Int {
    return 123
}
