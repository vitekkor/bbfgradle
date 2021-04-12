// Original bug: KT-40276

@Retention(AnnotationRetention.RUNTIME)
annotation class Ann

annotation class Test(
    @property:Ann
    val q: Int
)

fun main() {
    val qanns = Test::q.annotations
    println(qanns.map { it::class.simpleName })
}
