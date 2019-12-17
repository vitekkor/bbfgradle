inline fun tryZap( fn: (String) -> String) =
        fn(TODO()
        )
fun T()  = tryZap{ error ->  error }