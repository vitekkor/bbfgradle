// Original bug: KT-28956

fun test(monitor: Any) {
    synchronized(monitor) {
        run outer@{
            for (i in 1..100) {
                synchronized(monitor) {
                    inner@ for (j in 1..100) {
                        return@outer
                    }
                }
            }
        }
    }
}
