// Original bug: KT-10919

fun ifUnderLambda(list: MutableList<String>) {
    run {
        if (true) {
            list.apply { /* ... */ } // : MutableList<String>
        }
        else {
            list.add("") // : Boolean
            Unit // : Unit
        }
    }
}
