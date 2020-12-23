package helpers

import kotlin.coroutines.*
import kotlin.coroutines.intrinsics.*
import kotlin.coroutines.jvm.internal.CoroutineStackFrame


fun <T> handleResultContinuation(x: (T) -> Unit): Continuation<T> = object: Continuation<T> {
    override val context = EmptyCoroutineContext
    override fun resumeWith(result: Result<T>) {
        x(result.getOrThrow())
    }
}

fun handleExceptionContinuation(x: (Throwable) -> Unit): Continuation<Any?> = object: Continuation<Any?> {
    override val context = EmptyCoroutineContext
    override fun resumeWith(result: Result<Any?>) {
        result.exceptionOrNull()?.let(x)
    }
}

open class EmptyContinuation(override val context: CoroutineContext = EmptyCoroutineContext) : Continuation<Any?> {
    companion object : EmptyContinuation()
    override fun resumeWith(result: Result<Any?>) {
        result.getOrThrow()
    }
}

class ResultContinuation : Continuation<Any?> {
    override val context = EmptyCoroutineContext
    override fun resumeWith(result: Result<Any?>) {
        this.result = result.getOrThrow()
    }

    var result: Any? = null
}

abstract class ContinuationAdapter<in T> : Continuation<T> {
    override val context: CoroutineContext = EmptyCoroutineContext
    override fun resumeWith(result: Result<T>) {
        if (result.isSuccess) {
            resume(result.getOrThrow())
        } else {
            resumeWithException(result.exceptionOrNull()!!)
        }
    }

    abstract fun resumeWithException(exception: Throwable)
    abstract fun resume(value: T)
}


class TailCallOptimizationCheckerClass {
    private val stackTrace = arrayListOf<StackTraceElement?>()

    suspend fun saveStackTrace() = suspendCoroutineUninterceptedOrReturn<Unit> {
        saveStackTrace(it)
    }

    fun saveStackTrace(c: Continuation<*>) {
        if (c !is CoroutineStackFrame) error("Continuation " + c + " is not subtype of CoroutineStackFrame")
        stackTrace.clear()
        var csf: CoroutineStackFrame? = c
        while (csf != null) {
            stackTrace.add(csf.getStackTraceElement())
            csf = csf.callerFrame
        }
    }

    fun checkNoStateMachineIn(method: String) {
        stackTrace.find { it?.methodName?.startsWith(method) == true }?.let { error("tail-call optimization miss: method at " + it + " has state-machine " +
                stackTrace.joinToString(separator = "\n")) }
    }

    fun checkStateMachineIn(method: String) {
        stackTrace.find { it?.methodName?.startsWith(method) == true } ?: error("tail-call optimization hit: method " + method + " has no state-machine " +
                stackTrace.joinToString(separator = "\n"))
    }
}

val TailCallOptimizationChecker = TailCallOptimizationCheckerClass()

class StateMachineCheckerClass {
    private var counter = 0
    var finished = false

    var proceed: () -> Unit = {}

    fun reset() {
        counter = 0
        finished = false
        proceed = {}
    }

    suspend fun suspendHere() = suspendCoroutine<Unit> { c ->
        counter++
        proceed = { c.resume(Unit) }
    }

    fun check(numberOfSuspensions: Int, checkFinished: Boolean = true) {
        for (i in 1..numberOfSuspensions) {
            if (counter != i) error("Wrong state-machine generated: suspendHere should be called exactly once in one state. Expected " + i + ", got " + counter)
            proceed()
        }
        if (counter != numberOfSuspensions)
            error("Wrong state-machine generated: wrong number of overall suspensions. Expected " + numberOfSuspensions + ", got " + counter)
        if (finished) error("Wrong state-machine generated: it is finished early")
        proceed()
        if (checkFinished && !finished) error("Wrong state-machine generated: it is not finished yet")
    }
}

val StateMachineChecker = StateMachineCheckerClass()

object CheckStateMachineContinuation: ContinuationAdapter<Unit>() {
    override val context: CoroutineContext
        get() = EmptyCoroutineContext

    override fun resume(value: Unit) {
        StateMachineChecker.proceed = {
            StateMachineChecker.finished = true
        }
    }

    override fun resumeWithException(exception: Throwable) {
        throw exception
    }
}
