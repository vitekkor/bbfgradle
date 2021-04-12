// Original bug: KT-30846

fun foo() {
    val list = listOf(1)
    val emptyList = emptyList<Int>()
    val arrayList = arrayListOf(1)
    val array = arrayOf(1)

    if (list.size == 0) return
    if (emptyList.size != 0) return
    if (arrayList.size > 0) return // no warning reported
    if (array.size < 1) return

    val map = mapOf<Int, String>()
    val hashMap = hashMapOf<Int, String>()
    val linkedHashMap = linkedMapOf<Int, String>()

    if (map.size <= 0) return
    if (hashMap.size >= 1) return // no warning reported
    if (linkedHashMap.size != 0) return // no warning reported
}
