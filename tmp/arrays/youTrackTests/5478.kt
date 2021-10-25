// Original bug: KT-33347

private tailrec fun injectProperties(properties: Map<String, String>, str: String, depth: Int = 0): String {
            if (depth < 0) throw IllegalArgumentException("Negative depth")
            if (depth > 50) return str
            val propertyRegex = Regex("\\$\\{([^}]+)}")
            val found = propertyRegex.findAll(str).also { if (it.none()) return str }
            return injectProperties(
                properties,
                found.fold(str) { acc, curr ->
                    val propertyName = curr.groupValues[1]
                    val propertyValue = properties[propertyName] ?: return@fold acc
                    return@fold acc.replace(curr.value, propertyValue)
                },
                depth + 1
            )
        }
