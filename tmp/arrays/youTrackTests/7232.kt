// Original bug: KT-25954

fun main(args: Array<String>) {     
    val localAttributeA = LocalAttribute("1")
    val remoteAttributeB = RemoteAttribute("1")
    val remoteAttributeC = RemoteAttribute("1")
    val listA = listOf<ProductAttribute>(localAttributeA)
    val listB = listOf<ProductAttribute>(remoteAttributeB)
    val listC = listOf<ProductAttribute>(remoteAttributeC)

    println(remoteAttributeB.compareTo(remoteAttributeC)) //0 - equal - OK
    println(listB == listC) //equal - OK 
    
    println(localAttributeA.compareTo(remoteAttributeB)) //0 - equal - OK   
    println(listA == listB) //not equal - WHY? 
}


interface ProductAttribute : Comparable<ProductAttribute> {

    val productId: String

    override fun compareTo(other: ProductAttribute) 
    = compareValuesBy(this, other, ProductAttribute::productId)
}

data class LocalAttribute(override val productId:String): ProductAttribute { }

data class RemoteAttribute(override val productId:String): ProductAttribute { }

