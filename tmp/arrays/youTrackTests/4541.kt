// Original bug: KT-36028

class A {
    fun foo(){
        //start typing run. After applying the suggestion:
        kotlin.run {  }

        //start typing runCatching. After applying the suggestion:
        kotlin.runCatching {  }
    }
}
