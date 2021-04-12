// Original bug: KT-23962

import kotlin.reflect.jvm.internal.impl.protobuf.*

fun main(args: Array<String>) {
    GeneratedMessageLite.ExtendableMessage::class.java.genericInterfaces
}
