// Original bug: KT-27935

typealias IndexedDistance = Pair<Int, Double>
typealias Window = (Array<IndexedDistance>) -> List<IndexedDistance>

class Core(
        val window: Window
)
