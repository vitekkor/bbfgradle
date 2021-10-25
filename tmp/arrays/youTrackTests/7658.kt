// Original bug: KT-26339

public interface CoroutineStackFrame {
    public val callerFrame: CoroutineStackFrame? // point to the frame up the stack or null if its the last frame of coroutine
    public fun getStackTraceElement(): StackTraceElement? // null if it is "synthetic" and shall not be rendered
}
