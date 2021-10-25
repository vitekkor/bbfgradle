// Original bug: KT-44799

fun interface Action {
    fun run()
}

fun getActionObject(): Action {
    return object : Action {
        override fun run() {
            println("Hello from Object!")
        }
    }
}

fun getActionLambda(): Action {
    return Action { println("Hello from Lambda!") }
}
