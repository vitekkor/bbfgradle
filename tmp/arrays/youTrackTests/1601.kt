// Original bug: KT-19888

    fun foo(ints: Collection<Int>) {
        ints.forEach(fun(value: Int) {
            if (value == 0) // start typing here "return@forEach"
                print(value)
        })
    }
