// Original bug: KT-12963

fun jjj() {}
fun ggg() {}

fun main(args: Array<String>) {
    sequenceOf<() -> Unit>(
            ::jjj,
            { ggg() }
    ).forEach {
        it()
    }
}
