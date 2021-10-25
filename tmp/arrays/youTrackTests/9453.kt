// Original bug: KT-13654

    fun test1() {
        val f = fun() {
            val g = fun() {
                println("i am g")
            }

            println("i am f")
            g()
        }

        println("i am test1")
        f()
    }
