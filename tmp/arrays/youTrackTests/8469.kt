// Original bug: KT-21095

interface Callback {
    fun on(id: Int)
}

fun caller(id: Int) {

    val callerId = id // There is should not be inspection about inline variable 
    object : Callback {
        override fun on(id: Int) {
            if (id == callerId) {
                // do something
            }
        }
    }

}
