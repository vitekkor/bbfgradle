// Original bug: KT-37006

// !LANGUAGE: +UseGetterNameForPropertyAnnotationsMethodOnJvm
// WITH_RUNTIME

@Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")
@kotlin.internal.InlineOnly
inline var prop: String
    get() = "12"
    set(value) {}
