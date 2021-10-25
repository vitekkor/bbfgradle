// Original bug: KT-29594

class MethodWithParens {
    fun `(bla)`() {
        something {
            something {
                something {}
            }
        }
    }

    private fun something(function: () -> Any) {
        function()
    }

}

fun main() {
    MethodWithParens().`(bla)`()
}
