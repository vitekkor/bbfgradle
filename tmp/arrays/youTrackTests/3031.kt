// Original bug: KT-32486

private fun replaceVariables(str: String, mapping: Map<String, String>) =
            mapping.asSequence() /* marked as not needed */ .fold(str) { s, template ->
                s.replace("\$${template.key}", template.value)
            }
