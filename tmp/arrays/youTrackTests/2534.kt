// Original bug: KT-36126

   fun foo(): Any {
       return "Whatever"
   }

   fun main() {
       val bar: String
       when (val fooValue = foo()) {
           is String -> bar = fooValue.toLowerCase()
           is StringBuilder -> return
           is Int -> bar = (42 + fooValue).toString()
       }
   }
   