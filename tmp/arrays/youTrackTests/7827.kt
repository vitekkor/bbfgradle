// Original bug: KT-25404

import kotlin.reflect.full.memberFunctions
import kotlin.reflect.jvm.javaMethod

interface Sample {

}

fun main(args: Array<String>) {
    Sample::class.memberFunctions.forEach {
        it.javaMethod
    }
}
