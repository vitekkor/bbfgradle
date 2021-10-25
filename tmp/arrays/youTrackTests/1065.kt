// Original bug: KT-23017

inline operator fun <reified T : Enum<T>> T.rangeTo(that: T): Sequence<T> = 
        (this.ordinal..that.ordinal).asSequence().map { enumValues<T>()[it] }
