// Original bug: KT-32228

fun main() {
    val result = Result.runCatching { "Test 01" }
    val errorResult = Result.runCatching { throw java.io.IOException("Some error happened") }
    
    println("Test")
    println(test("Test 01", result))
    println(test("Some error happened", errorResult))
    
    println()
    println("With generics")
    println(match("Test 01", "Some error happened").test(result))
    println(match("Test 01", "Some error happened").test(errorResult))
    
    println()
    println("With kotlintest dsl-like")
    println(result should match("Test 01", "Some error happened"))
    println(errorResult should match("Test 01", "Some error happened"))
}

infix fun <T> T.should(matcher: Matcher<T>) = matcher.test(this)

fun test(expectedValue: String, result: Result<String>) = if (result.isSuccess) {
    expectedValue == result.getOrNull()
} else {
    expectedValue == result.exceptionOrNull()?.message
}

fun <T> match(expectedValue: T, errorMessage: String) = object: Matcher<Result<T>> {
    override fun test(value: Result<T>) = if (value.isSuccess) {
        print("I'm a success")
        Pair(
            value.getOrNull() == expectedValue,
            Pair(value.getOrNull(), expectedValue)
        )
    } else {
        print("I'm an error")
        Pair(
            value.exceptionOrNull()?.message == errorMessage,
            Pair(value.exceptionOrNull()?.message, errorMessage)
        )
    }
}

interface Matcher<T> {
    fun test(value: T): Pair<Boolean, Pair<Any?, Any?>>
}
