// Original bug: KT-18632

fun <T> nonDefaultParameter(i: T, paramFun: (T) -> T) {
    // SMART_STEP_INTO_BY_INDEX: 0
    // RESUME
    //Breakpoint!
    paramFun(i)
}

fun main(args: Array<String>) {
    val localFun : (Int) -> Int = { it + 1 }

    nonDefaultParameter(12, localFun)
}
