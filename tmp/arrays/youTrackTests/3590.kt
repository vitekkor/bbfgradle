// Original bug: KT-38392

  private fun byteToInt(b: ByteArray): Int {
        var i = 0
        var result = 0
        var shift = 0
        while (i < b.size) {
            val be = b[i].toInt()
            result = result or ((be and 0xff) shl shift)
            shift += 8
            i += 1
        }
        return result
    }
