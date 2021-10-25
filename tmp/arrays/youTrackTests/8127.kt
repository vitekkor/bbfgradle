// Original bug: KT-23216

package a

class A {
    @Deprecated("deprecated", replaceWith = ReplaceWith("sample()", imports = ["otherPackage.*"]))
    @Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")
    @kotlin.internal.LowPriorityInOverloadResolution
    fun sample() {}

    fun test() {
        sample() // invoke ReplaceWith action
    }
}
