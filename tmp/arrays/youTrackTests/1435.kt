// Original bug: KT-30221

// module "core"
suspend fun suspendedCast(arg: String) = arg as Any

suspend inline fun throwCompilerError(token: String?) {
    token?.let { suspendedCast(it) }
}

// module "sample"
suspend fun main() {
    // this will throw a compiler error as it is in a separate module
    throwCompilerError("any string or null")
}
