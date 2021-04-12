// Original bug: KT-24477

class Container(val item: Item = Item())
class Item(val i: Int = 3)

class Test {
    var arrayOfContainers = arrayOf<Container>()

    fun setupAndGet(): Container {
        setup(1)
        return arrayOfContainers[0]
    }

    inline fun setup(number: Int, getItem: ((Int) -> Item?) = { null }) {
        if (number <= 0) return

        arrayOfContainers = Array(number) {
            val item = getItem(it)
            if (item != null)
                Container(item)
            else
                Container()
        }
    }
}


fun main(args: Array<String>) {
    val t = Test()
    t.setup(3) { Item(4) }
    t.setupAndGet()
}
