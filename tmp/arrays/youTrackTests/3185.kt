// Original bug: KT-39405


interface IError

enum class Error : IError {
    A
}

enum class CommonError : IError {
    C
}

fun main() {
    val error = if (true) Error.A else CommonError.C // inferred {Enum & IError}> & IError}> & IError}> & IError}> & IError}
}
