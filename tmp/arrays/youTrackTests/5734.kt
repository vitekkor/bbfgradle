// Original bug: KT-23679

import kotlin.reflect.KFunction1
import kotlin.reflect.KFunction2

val navigation1: KFunction1<String, Int>? = null
val navigation2: KFunction2<String, String, Int>? = null
