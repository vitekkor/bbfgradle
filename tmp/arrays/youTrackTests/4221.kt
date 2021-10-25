// Original bug: KT-29242

fun main(args: Array<String>) {
    println(causesVerifyErrorSample())
}

fun causesVerifyErrorSample(): Sample {
    val success = Sample
        // Calling Type with generic T
        .Success(true)
    
    return success
        .flatMap { Sample.Failure(RuntimeException()) }
}


sealed class Sample {

    inline fun flatMap(f: (Any) -> Sample): Sample =
        when (this) {
            // First clause been Type with generic Nothing
            is Failure -> this
            is Success -> f(this.value)
        }

    data class Failure(val exception: Throwable): Sample()
    data class Success(val value: Any): Sample()
}
