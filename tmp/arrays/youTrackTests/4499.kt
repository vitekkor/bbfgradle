// Original bug: KT-36244

fun doCrash2() {
    @Suppress("test")
    if (Math.random() > -1) {
        if (Math.random() < -1) {
            val x = System.currentTimeMillis()
        } else {
            true
        }
    }
}
