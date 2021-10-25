// Original bug: KT-26871

class DataLoader {
    lateinit var data: String
    var dataLength: Int = 0
    fun loadAndMeasureData() {
        data = loadData()
        dataLength = data.length
    }

    private fun loadData(): String {
        TODO()
    }
}
