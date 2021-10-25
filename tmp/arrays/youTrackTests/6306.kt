// Original bug: KT-26412

data class A(val x: String, val y: String)

suspend fun foo(a: A, block: suspend (Int, A, String) -> String): String = block(1, a, "#")

suspend fun test() = foo(A("O", "K")) { i_param, (x_param, y_param), v_param ->
    i_param.toString() + x_param + y_param + v_param
}
