// Original bug: KT-41191

public interface Test {
    public fun memberFun()
    public fun String.extension()

    public companion object : Test by TestImpl
}

internal object TestImpl : Test {
    override fun memberFun() = TODO()
    override fun String.extension() = TODO()
}
