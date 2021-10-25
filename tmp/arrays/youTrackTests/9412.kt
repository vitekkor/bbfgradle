// Original bug: KT-10887

public var Throwable.stackTrace: Array<StackTraceElement>
    get() = (this as java.lang.Throwable).stackTrace
    set(value) {
        (this as java.lang.Throwable).stackTrace = value
    }
