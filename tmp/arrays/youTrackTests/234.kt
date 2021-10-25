// Original bug: KT-44801

import kotlin.experimental.ExperimentalTypeInference

interface Callback {
    fun onSuccess()
}

public interface SendChannelX<in E> {
    public fun close(cause: Throwable? = null): Boolean
}

public interface ProducerScopeX<in E>   {

    public val channel: SendChannelX<E>
}

public interface FlowX<out T> {
}

@OptIn(ExperimentalTypeInference::class)
public fun <T> callbackFlowX(@BuilderInference block: ProducerScopeX<T>.() -> Unit): FlowX<T> = TODO("...")

fun foo(): FlowX<String> = callbackFlowX {
    object : Callback {
        override fun onSuccess() {
            channel.close() // <=== receiverType: SendChannelX<NonFixed: TypeVariable(T) /* = ERROR_CLASS */ >
        }
    }
}

fun box(): String {
    foo()
    return "OK"
}
