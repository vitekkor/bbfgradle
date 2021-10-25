// Original bug: KT-9614

public inline fun todo1(block: ()-> Any): Nothing {
    println("TODO at " + (Exception() as java.lang.Throwable).getStackTrace()?.get(1))
    throw NotImplementedError()
}
