// Original bug: KT-12960

class Stuff : () -> Stuff {
    override fun invoke(): Stuff {
        return this
    }
}
fun unwrap(s: () -> Stuff) {
    s.invoke()
}
fun main() {
    unwrap(Stuff())
}