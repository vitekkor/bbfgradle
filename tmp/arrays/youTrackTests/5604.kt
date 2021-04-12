// Original bug: KT-33269

fun foo(n: Int) {}
val fail1: (Int) -> Unit =
    if (true) ::foo
    else { n: Int -> } // [TYPE_MISMATCH] Type mismatch: inferred type is (Int) -> Unit but KFunction1<Int, Unit> was expected
val fail2: (Int) -> Unit =
    if (true) ::foo
    else fun(n: Int) {} // [TYPE_MISMATCH] Type mismatch: inferred type is (Int) -> Unit but KFunction1<Int, Unit> was expected
