// Original bug: KT-27843

import java.net.InetAddress

fun main(args: Array<String>) {

    // Should fail here:
    val address = InetAddress.getLocalHost()

    val hostname = address.canonicalHostName

    println("Canonical name of the localhost is: $hostname")
}
