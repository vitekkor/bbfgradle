// Original bug: KT-11884

package ws // Error:(1, 1) Kotlin: Duplicate JVM class name 'ws/WsKt' generated from: package-fragment ws, WsKt

fun test() {}

class WsKt // Error:(5, 1) Kotlin: Duplicate JVM class name 'ws/WsKt' generated from: package-fragment ws, WsKt
