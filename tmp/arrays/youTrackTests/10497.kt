// Original bug: KT-1946

val (() -> Unit).r : Runnable
    get() = object : Runnable {
        public override fun run() {
            this@r() // @r is not resolved
        }

    }
