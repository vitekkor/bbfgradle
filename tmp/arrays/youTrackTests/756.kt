// Original bug: KT-41607

import kotlin.random.Random

fun main(args: Array<String>) {
    var enumVar: SomeEnum? = null
    val result = if (Random.nextBoolean()) {
        "result"
    } else {
        enumVar = try {
            SomeEnum.valueOf("X")
        } catch (e: Exception) {
            throw RuntimeException()
        }
        enumVar!!.name // !!! HERE in 1.4 enumVar should be null-asserted otherwise compilation error
    }
    print(enumVar)
    print(result)
}

enum class SomeEnum {
    FOO, BAR
}
