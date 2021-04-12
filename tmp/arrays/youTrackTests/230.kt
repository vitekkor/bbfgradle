// Original bug: KT-45702

import kotlin.experimental.ExperimentalTypeInference
import kotlin.reflect.KProperty1

class SomeClass(val prop: String)
@OptIn(ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
fun typedFunc(parse: () -> Int): Int = parse()

@OptIn(ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
fun typedFunc(parse: () -> String): String = parse()

infix fun <T, V> KProperty1<T, V>.with(parsed: V) = parsed

fun main() {
    SomeClass::prop with typedFunc{ 100 }  
    SomeClass::prop with typedFunc{"my bad"} 
}
