// Original bug: KT-41994

public interface CustomOrdinal {
    public val customOrdinals: ByteArray
}

public enum class EvalMethod(vararg bytes: Byte) : CustomOrdinal {
    PERMANENT_OFF_STATE(0), PERMANENT_SAFE_ON_STATE(1), SLEEP_MODE(3), SINGLE_STATIC_PROTECTIVE_FIELD(4, 14),
    SINGLE_STATIC_WARNING_FIELD(5, 15), CONTOUR_AS_REFERENCE(10);
    
    override val customOrdinals: ByteArray = bytes
}
