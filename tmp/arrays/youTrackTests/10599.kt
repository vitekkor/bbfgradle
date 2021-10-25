// Original bug: KT-3682

package big.delight.`in`.every.byte

import java.lang.Byte as JByte // TODO: make constants available

fun main(args : Array<String>) {
    for (b in JByte.MIN_VALUE..JByte.MAX_VALUE) {
        println(b)
    }
}
