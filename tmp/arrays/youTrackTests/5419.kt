// Original bug: KT-3742

import java.util.logging.Logger

val LOG: Logger = Logger.getAnonymousLogger()

fun fox() {
    val toLog: Any?
    try {       
    }
    catch (e: Exception) {
        toLog = Runnable {
            LOG.info("123")
            LOG.info("456")
        }
    }
}
