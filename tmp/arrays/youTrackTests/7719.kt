// Original bug: KT-25932

interface ProductAttribute : Comparable<ProductAttribute> {

    val productId: String

    override fun compareTo(other: ProductAttribute) =
            compareValuesBy (this, other, ProductAttribute::productId)
}
