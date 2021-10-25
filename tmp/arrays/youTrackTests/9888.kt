// Original bug: KT-10922

/**
 * Returns the average of a list of shorts
 * @param list the list to average
 * @return the average value of the list
 */
fun<ElementType : Short> Average(list: AbstractList<ElementType>) : ElementType
{
    var total : Int = 0
    for(item: ElementType in list){
        total += (item as Short)
    }
    total /= list.size
    return total as ElementType
}

////TODO Report Error
///**
// * Returns the average of a list of shorts
// * @param list the list to average
// * @return the average value of the list
// */
//fun<ElementType : Short> Average(list: AbstractList<ElementType>) : ElementType
//{
//    var total : Short = 0 as Short
//    for(item: ElementType in list){
//        total += (item as Short)
//    }
//    total /= list.size
//    return total as ElementType
//}
