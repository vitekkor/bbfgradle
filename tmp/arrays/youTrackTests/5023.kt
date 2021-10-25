// Original bug: KT-34628

inline fun <T, R> ((T) -> R).mix(forwardDeclaration: ((T) -> R) -> (T) -> R): (T) -> R {
    return forwardDeclaration(this)
}

inline fun <T, R> create(crossinline declaration: (T) -> R): (T) -> R {
    return { input: T ->
        declaration(input)
    }
}

val provider = create { input: String ->
    input /*provides some values*/
}.mix { intermediateProvider ->
    //this could be an inline function call as well
    { input -> "rejected" + intermediateProvider(input) }
}
