interface A {
    fun a()
}

class B: A {
    context(Int)
    override fun a() = TODO()
}