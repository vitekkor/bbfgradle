// Original bug: KT-17633

package x

import java.lang.System.*

/**
 * Created by cdr on 24.04.2017.
 */
fun topf() {
    out.println("hello");

    val actionClassMap: Map<Action, Class<out LogEvent>> = mapOf(
            Action.COMPLETION_STARTED to CompletionStartedEvent::class.java,
            Action.TYPE to LogEvent::class.java
    )

}

abstract class LogEvent(@Transient var userUid: String, sessionId: String, type: Action) {
    @Transient var recorderId = "completion-stats"
    @Transient var timestamp = System.currentTimeMillis()
    @Transient var sessionUid: String = sessionId
    @Transient var actionType: Action = type

    fun accept(visitor: String){}
}

class CompletionStartedEvent(
        var ideVersion: String,
        var pluginVersion: String,
        var mlRankingVersion: String,
        userId: String,
        sessionId: String,
        var language: String?,
        var performExperiment: Boolean,
        var experimentVersion: Int,
        completionList: List<Action>,
        selectedPosition: Int)
    :LogEvent(userId, sessionId, Action.TYPE)
{}

enum class Action {
    COMPLETION_STARTED,
    TYPE,
    BACKSPACE,
    UP,
    DOWN,
    COMPLETION_CANCELED,
    EXPLICIT_SELECT,
    TYPED_SELECT,
    CUSTOM
}