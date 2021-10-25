// Original bug: KT-41135

import Helper.badTypeInference
import kotlin.reflect.KProperty

class TypeInference {
    private val fails: Int by badTypeInference() // OK
    private val succeeds by badTypeInference<TypeInference, Int>() // OK
}

object Helper {
    inline fun <T, reified P> T.badTypeInference() = object : PropertyDelegateProvider<T, P>() {
        override fun provideDelegate(thisRef: Any, property: KProperty<*>) = lazy { P::class.java.newInstance() }
    }

    abstract class PropertyDelegateProvider<T, R> {
        abstract operator fun provideDelegate(
            thisRef: Any,
            property: KProperty<*>
        ): Lazy<R>
    }
}
