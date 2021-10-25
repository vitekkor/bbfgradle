// Original bug: KT-19205

package test

@Deprecated("message")
class D

typealias DA = D
// ^ Warning:(6, 16) Kotlin: 'D' is deprecated. message

fun foo(): DA = TODO()
// ^ Warning:(9, 12) Kotlin: 'D' is deprecated. message
