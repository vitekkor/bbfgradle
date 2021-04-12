// Original bug: KT-2309

fun bar() {}

fun baz() {}

fun foo() {

    var completedSuccessfully = false // incorrect VARIABLE_WITH_REDUNDANT_INITIALIZER here
    try {
        bar()
        completedSuccessfully = true
    } finally {
        if (completedSuccessfully) {
            baz()
        }
    }

}
