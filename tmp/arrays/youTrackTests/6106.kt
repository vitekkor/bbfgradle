// Original bug: KT-19995


import kotlin.Array
import kotlin.String
import kotlin.UnsupportedOperationException as UOE

fun main(args: Array<String>) {
    throw UOE("not implemented")
}
