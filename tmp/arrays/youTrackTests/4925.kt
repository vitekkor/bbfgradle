// Original bug: KT-35002

inline fun foo() {} // [NOTHING_TO_INLINE] Expected performance impact from inlining is insignificant. Inlining works best for functions with parameters of functional types
