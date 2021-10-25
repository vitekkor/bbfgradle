// Original bug: KT-23104

private val listVisitor =
    { list: MutableList<Char>, action: (MutableList<Char>, Int) -> Unit ->
                list.indices.reversed()
                    .filter { list[it] in '0'..'9' }
                    .forEach { action(list, it) }
    }
