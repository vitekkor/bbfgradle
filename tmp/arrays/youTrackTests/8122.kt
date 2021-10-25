// Original bug: KT-23104

private val listVisitor =
    { list: MutableList<Char>, action: (MutableList<Char>, Int) -> Unit ->
        list.withIndex()
            .reversed()
            .filter { (_, c) -> c in '0'..'9' }
            .forEach { (index, _) -> action(list, index) }
    }
