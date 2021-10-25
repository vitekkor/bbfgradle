// Original bug: KT-13108

data class Product(val id: Int)
data class HasProduct(val product: Product)

object ProductRepository {
    fun get(id: Int) = Product(id)  // Assume itâs fetching product from some data-base
}

fun process(product: Product): Unit {
    TODO("something with âproductâ instance")
}

fun process(productIdAsInt: Int): Unit {
    process(ProductRepository.get(productIdAsInt))
}

fun process(productIdAsString: String): Unit {
    process(ProductRepository.get(productIdAsString.toInt()))
}

fun process(productContainer: HasProduct): Unit {
    process(productContainer.product)
}
