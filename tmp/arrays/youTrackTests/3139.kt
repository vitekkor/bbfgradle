// Original bug: KT-38751

interface SourceInstrumentId {
    val value: Long
}

class NodalPositionInstrumentId(override val value: Long) : SourceInstrumentId
inline class SomethingImNotUsing(override val value: Long) : SourceInstrumentId
