// Original bug: KT-17242

fun main(args: Array<String>) {
    createRunnable {
        object : Runnable {
            override fun run() {
                println(SomeClass("hello")) // NoSuchMethodError: RunMeKt$main$1$1$SomeClass.<init>(LRunMeKt$main$$inlined$createRunnable$1$lambda$1;Ljava/lang/String;)V
            }

            inner class SomeClass(var prop: String)
        }.run()
    }.run()
}

inline fun createRunnable(crossinline code: ()->Unit): Runnable = object : Runnable {
    override fun run() = code()
}
