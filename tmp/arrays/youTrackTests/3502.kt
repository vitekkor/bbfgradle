// Original bug: KT-37249

fun case2(x: Any){
    when (x){
        1 -> try { {"1"}; ""; TODO()}catch (e: Exception) { { }} //Type mismatch.
        "1" -> try { 1 }catch (e: Exception) { { }} //Type mismatch.
        else -> try { 1 }catch (e: Exception) { {1 }} //Type mismatch.
    }
    when (x){
        1 -> try { {"1"}; ""; TODO(); ""}catch (e: Exception) { { }} //ok
        "1" -> try { 1 }catch (e: Exception) { { }} //ok
        else -> try { 1 }catch (e: Exception) { {1 }} //ok
    }
    when (x){
        1 -> try { {"1"}; ""; TODO()}catch (e: Exception) { { }} //ok
        "1" -> try { 1; "" }catch (e: Exception) { { }} //ok
       else -> try { 1 }catch (e: Exception) { {1 }} //ok
   }
}
