// Original bug: KT-41106

enum class LogLevel {
    ERROR,
    WARNING,
    INFO,
    DEBUG
}

class Logger {
    companion object default {
        fun log(level: LogLevel = LogLevel.ERROR, message: String, completion: (Boolean) -> Unit) { }
    }
}
