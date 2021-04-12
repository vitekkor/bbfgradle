// Original bug: KT-42435

interface Toggled {
    val onEnabled: Event<() -> (Unit)> 
    val onDisabled: Event<() -> (Unit)>
}
typealias Event<Listener> = Toggled.(Listener) -> (Unit)
