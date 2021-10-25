// Original bug: KT-45431

import kotlin.reflect.KProperty

class DiProperty<out T : Any>(private val provider: () -> T) {
    operator fun provideDelegate(receiver: Any?, prop: KProperty<Any?>): Lazy<T> = lazy(provider)
}

object Di {
    inline fun <reified T : Any> inject(): DiProperty<T> = DiProperty { TODO() }
}

class IrPropertyIssue {

    companion object {

        private val property: String by Di.inject()

        @JvmStatic
        fun test() {

        }
    }
}

fun main() {
    IrPropertyIssue.test()
}
