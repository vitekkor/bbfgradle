// Original bug: KT-9519

interface IWithToString {
    override fun toString(): String
}

class DefaultToString : IWithToString {
    override fun toString(): String = "OK"
}

// Class 'DelegatingToDefault' must be declared abstract or implement 
//      abstract member public abstract fun toString(): kotlin.String 
//      defined in IWithToString
class DelegatingToDefault : IWithToString by DefaultToString()

fun box() = DelegatingToDefault().toString()
