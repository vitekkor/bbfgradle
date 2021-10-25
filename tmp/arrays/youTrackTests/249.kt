// Original bug: KT-45686

    /**
     * A version of println which prints [this] and returns [this], facilitating fluent interfaces, functional programming, and assisting in debugging.
     * Also useful for debugging values without breaking a call chain or interrupting the flow of code for logging values.
     * */
    fun <T> T.println() = this.apply { kotlin.io.println(this) }

    /**
     * A version of println which which prints [message] and returns [this], facilitating fluent interfaces, functional programming, and assisting in debugging.
     * Intended for debugging values without breaking a call change or interrupting the flow of code for logging values.
     * */
    fun <T, R> T.println(message: R) = this.apply { kotlin.io.println(message) }

    /**
     * A version of println which passes [this] into [message] to create the message to pass to println, and returns [this], facilitating fluent interfaces, functional programming, and assisting in debugging.
     * Also useful for debugging values without breaking a call change or interrupting the flow of code for logging values.
     * */
    fun <T, R> T.println(message: (T) -> R) = this.apply { kotlin.io.println(message(this)) }
