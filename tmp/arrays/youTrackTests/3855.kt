// Original bug: KT-24551

/**
 * Reroute vertx logging from JUL to SLF4J
 * @see io.vertx.core.logging.Logger
 */
internal object LoggingConfigurator {

    init {
        /**
         * Reroute vertx logging from JUL to SLF4J
         * @see io.vertx.core.logging.Logger
         */
        System.setProperty("vertx.logger-delegate-factory-class-name", "io.vertx.core.logging.SLF4JLogDelegateFactory")
    }
}
