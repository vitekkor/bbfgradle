// Original bug: KT-30267

fun testElvis() {
   var a: Any? = null

   while (true) {
       a ?: return
   }

   a // Any?
}

fun testElvis2() {
   var a: Any? = null

   while (true) {
       a ?: return
       a
   }

   a // Smart cast to Any
}

fun testElvisExpansion() {
   var a: Any? = null

   while (true) {
       if (a == null) return
   }

   a // Smart cast to Any
}
