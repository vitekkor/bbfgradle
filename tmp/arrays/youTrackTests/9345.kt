// Original bug: KT-14304

class Demo {
    private val some = object {
        fun foo() {
            if (state)
                state = true

            println(state) // must be initialized
        }
    }

    private val some2 = object {
        fun foo() {
            if (state)
                state = true
            else
                state = false

            println(state) // OK
        }
    }
    
    private val some3 = run {
        if (state)
            state = true

        println(state) // OK
    }

    private var state: Boolean = true
}
