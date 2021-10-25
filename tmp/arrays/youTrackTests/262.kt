// Original bug: KT-44299

package pack.one

sealed class KotlinSealed 

class Impl : KotlinSealed()

fun check(c: KotlinSealed): Unit = when (c) { //[NO_ELSE_IN_WHEN] 'when' expression must be exhaustive, add necessary 'is InheritKotlin' branch or 'else' branch instead
    is Impl -> TODO()
}
