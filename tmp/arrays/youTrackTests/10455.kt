// Original bug: KT-5594

fun foo(f: () -> Unit) {}

fun test() {
    foo {
//        val actionId: Any = 1
        val item: Any? = 1
        if (item != null){
// In original version, as I remember, `when` was an important to reproduce, but now it is not.
//            when(actionId){
//                1 -> { 1 }
//                "2" -> { "2"}
//                else -> {}
//            }
        }
    }

}
