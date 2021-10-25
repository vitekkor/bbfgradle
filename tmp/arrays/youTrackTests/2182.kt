// Original bug: KT-29852

private const val pathSeparator = '/'

private fun countNameParts(path: String): Int {
        if (path.isEmpty()) {
            return 1
        }

        var currentIndex = 0
        var result = 0

        while (currentIndex < path.length) {
            if (path[currentIndex++] != pathSeparator) {
                ++result

                while (currentIndex < path.length && path[currentIndex] != pathSeparator) {
                    ++currentIndex
                }
            }
        }

        return result
    }
