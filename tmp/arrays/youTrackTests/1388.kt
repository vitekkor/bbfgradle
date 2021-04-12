// Original bug: KT-35752

typealias TeamContext = Scope<Team, TeamScope>.Context
typealias AnyContext = Scope<out Any?, *>.Context

class Team

abstract class Scope<T, S : Scope<T, S>> {
    open inner class Context(val context: T, val scope: S, parentContext: AnyContext? = null)
}

object TeamScope : Scope<Team, TeamScope>()
