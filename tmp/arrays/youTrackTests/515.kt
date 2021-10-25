// Original bug: KT-41257

import java.io.DataInput

fun read(input: DataInput): Array<String> {
    val bytesLength = input.readInt()
    val bytes = ByteArray(bytesLength)
    input.readFully(bytes, 0, bytesLength)
    val stringsLength = input.readInt()
    return Array<String>(stringsLength) { input.readUTF() }
}
