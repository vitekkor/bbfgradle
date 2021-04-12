// Original bug: KT-7882

public inline fun <T, R> with(receiver: T, f: T.() -> R   ): R    = receiver.f()
public inline fun <T>    with(receiver: T, f: T.() -> Unit): Unit = receiver.f()

val v = with (StringBuilder()) { toString() }
