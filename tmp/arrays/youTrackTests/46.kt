// Original bug: KT-20094

package test1

private interface PrivateInFile {
    private class Private

    fun expose() = Private()
}

// Exposes 'PrivateInFile$Private' via 'expose'
class Derived : PrivateInFile
