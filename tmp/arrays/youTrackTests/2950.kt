// Original bug: KT-24106

private suspend fun privateSuspendTL() {
    println("Hello from private suspend top-level fun")
}

private fun regularPrivateTL() {
    println("Hello form private top-level fun")
}
