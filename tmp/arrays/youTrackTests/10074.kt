// Original bug: KT-9711

fun main(args: Array<String>) {
    IssueState.FIXED.ToString()
}

enum class IssueState {
    
    FIXED {
        override fun ToString() = D().k

        fun s()  = "1"

        inner class D {
            val k = s()
        }
    };

    open fun ToString() : String = "O"
}

