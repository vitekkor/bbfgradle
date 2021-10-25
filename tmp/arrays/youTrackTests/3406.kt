// Original bug: KT-38699

    data class Test(val str: String, val strArray: Array<String>? = null) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Test) return false

            if (str != other.str) return false
            if (strArray != null) {
                if (other.strArray == null) return false
                if (!strArray.contentEquals(other.strArray)) return false
            } else if (other.strArray != null) return false

            return true
        }

        override fun hashCode(): Int {
            var result = str.hashCode()
            result = 31 * result + (strArray?.contentHashCode() ?: 0)
            return result
        }
    }
