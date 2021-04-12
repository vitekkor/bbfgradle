// Original bug: KT-28096

open class CoroutineContext {
    
    open operator fun plus(other: CoroutineContext): CoroutineContext  {
        return other
    }       
    
    class Element : CoroutineContext() {

        @Deprecated(level = DeprecationLevel.ERROR, message = "Prohibited")
        override operator fun plus(other: CoroutineContext): CoroutineContext { return this }

        @Deprecated(level = DeprecationLevel.ERROR, message = "Also prohibited")
        operator fun plus(other: CoroutineContext.Element): CoroutineContext.Element { return this }
    }
}
