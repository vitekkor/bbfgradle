// Original bug: KT-8460

import java.util.*

fun main(args: Array<String>) {
    val uuid = "938B500F-8E94-4CB0-AEE8-B2FA5190CC43"
    val list = listOf(UUID.fromString(uuid))
    f(list)
}

fun f(x : Iterable<UUID>)  {
    println("938B500F-8E94-4CB0-AEE8-B2FA5190CC43" in x); // forgot to convert string to UUID
}
