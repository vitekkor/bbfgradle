// Original bug: KT-43017

class Repro {

    interface Action {
        fun invoke()
    }
    
    fun repro(action: Action) {
        invoke(action::invoke)
    }

    fun invoke(action: suspend () -> Unit) {

    }
}
