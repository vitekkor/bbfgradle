// Original bug: KT-5235

/**
 * Executes f with receiver, returning receiver
 */
public inline fun <T : Any> T.onWith(f: T.() -> Unit): T { f(); return this }

/**
 * Executes f with argument, returning argument
 */
public inline fun <T : Any> T.on(f: (T) -> Unit): T { f(this); return this }
