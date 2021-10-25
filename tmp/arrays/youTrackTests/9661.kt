// Original bug: KT-7749

interface ReadOnlySlice {
    val key: KeyWithSlice<ReadOnlySlice>
}

abstract class KeyWithSlice<out Slice : ReadOnlySlice>() {
    abstract val slice: Slice
}

abstract class KeyWithWritableSlice() : KeyWithSlice<WritableSlice>(), WritableSlice {
    override val slice: WritableSlice
        get() = this
}

interface WritableSlice : ReadOnlySlice {
    override val key: KeyWithWritableSlice
}
