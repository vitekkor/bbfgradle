// Original bug: KT-45408

package com.instacart.client.logging

/**
 * Abstraction of logging so that logging can be performed on both Android and JVM modules
 */
object ICLog {
    const val DEBUG = 3

    private var logging: ((level: Int, message: String?, t: Throwable?) -> Unit)? = null
    private var tag: ((tag: String) -> Unit)? = null
    @PublishedApi internal var isDebugLoggingEnabled = false

    fun init(
        logging: (level: Int, message: String?, throwable: Throwable?) -> Unit,
        tag: (tag: String) -> Unit,
        isDebug: Boolean,
    ) {
        this.logging = logging
        this.tag = tag
        this.isDebugLoggingEnabled = isDebug
    }

    /** Log a debug message */
    @JvmStatic fun d(message: String?) {
        log(DEBUG, message)
    }

    /** Log a debug exception and a message */
    @JvmStatic fun d(t: Throwable?, message: String?) {
        log(DEBUG, message, t)
    }

    /** Log a debug exception. */
    @JvmStatic fun d(t: Throwable?) {
        log(DEBUG, null, t)
    }

    @JvmStatic fun log(level: Int, t: Throwable? = null) {
        log(level, null, t)
    }

    @JvmStatic fun tag(tag: String): ICLog {
        this.tag?.invoke(tag)
        return this
    }

    fun log(level: Int, message: String?, t: Throwable? = null) {
        logging?.invoke(level, message, t)
    }
}
