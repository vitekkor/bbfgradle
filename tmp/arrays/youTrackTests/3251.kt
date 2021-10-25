// Original bug: KT-32514

package testhelpers

/** Returns the contents of a resources file located at self */
fun String.asResource(): String {
    if (!this.startsWith("/")) {
        throw Exception("Invalid resource path")
    }

    return this.javaClass.getResource(this).readText()
}
