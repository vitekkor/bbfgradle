// Original bug: KT-26038

class RecTest {
    val otherInstance = RecTest()

    // Compiler and IntelliJ complain that this isn't tail recursive
    tailrec fun differentReceiver() {
        return otherInstance.differentReceiver()
    }

    // Correctly processed as tail recursive
    tailrec fun thisReceiver() {
        return this.thisReceiver()
    }
}

// Correctly processed as tail recursive
tailrec fun noReceiver(recTest: RecTest) {
    return noReceiver(recTest.otherInstance)
}
