// Original bug: KT-25954

fun main(args: Array<String>) {     
    val localAttributeA = LocalAttribute("1")
    val remoteAttributeB = RemoteAttribute("1")
    val remoteAttributeC = RemoteAttribute("1")
    val listA = listOf<ProductAttribute>(localAttributeA)
    val listB = listOf<ProductAttribute>(remoteAttributeB)
    val listC = listOf<ProductAttribute>(remoteAttributeC)
   
    println(remoteAttributeB.equals(remoteAttributeC)) //0 - equal - OK
    println(listB == listC) //equal - OK 
    
    println(localAttributeA.equals(remoteAttributeB)) //0 - equal - OK   
    println(listA == listB) //not equal - WHY? 
}
interface ProductAttribute {
    val productId: String
}

data class LocalAttribute(override val productId:String): ProductAttribute { 
   fun equals(productAttribute: ProductAttribute) = productId == productAttribute.productId
}

data class RemoteAttribute(override val productId:String): ProductAttribute { 
   fun equals(productAttribute: ProductAttribute) = productId == productAttribute.productId
}
