// Original bug: KT-36840

enum class A { V1 }

fun testVariableAssignment_throws(a: A) {
    val x: Int
    when (a) {
        A.V1 -> x = 11
        // else -> throw
    }
}

fun testStatement(a: A) {
    when (a) {
        A.V1 -> 1
        // else -> {}
    }
}

fun testParenthesized_throws(a: A) {
    (when (a) {
        A.V1 -> 1
        // else -> throw
    })
}

fun testAnnotated_throws(a: A) {
    @Suppress("") when (a) {
        A.V1 -> 1
        // else -> throw
    }
}

fun testExpression_throws(a: A) =
    when (a) {
        A.V1 -> 1
        // else -> throw
    }

fun testIfTheElseStatement(a: A, flag: Boolean) {
    if (flag)
        0
    else {
        when (a) {
            A.V1 -> 1
            // else -> {}
        }
    }
}

fun testIfTheElseParenthesized_throws(a: A, flag: Boolean) {
    (if (flag)
        0
    else {
        when (a) {
            A.V1 -> 1
            // else -> throw
        }
    })
}

fun testIfTheElseAnnotated_throws(a: A, flag: Boolean) {
    @Suppress("")
    if (flag)
        0
    else {
        when (a) {
            A.V1 -> 1
            // else -> throw
        }
    }
}

fun testLambdaResultExpression_throws(a: A) {
    {
        when (a) {
            A.V1 -> 1
            // else -> throw
        }
    }()
}
