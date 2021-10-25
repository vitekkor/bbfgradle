// Original bug: KT-19073

interface Data
val data: Data? = null

class Recycler(val adapter: DataAdapter)
val recycler = Recycler(DataAdapter())

class DataAdapter {
    fun newData(data: Data) {}
}

fun main(args: Array<String>) {
    data?.let { recycler.adapter.newData(it) }
}
