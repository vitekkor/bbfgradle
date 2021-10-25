// Original bug: KT-30060

import java.util.concurrent.CancellationException

inline fun <T> Logger.runAndLogException(runnable: () -> T): T? {
    try {
        runnable()
        throw Throwable()
    }
    catch (e: CancellationException) {
        throw e
    }
    catch (e: Throwable) {
        return null
    }
}

class Logger

fun main() {
    Logger().runAndLogException {  }
}
