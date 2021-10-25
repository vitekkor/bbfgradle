// Original bug: KT-10259

class BookReaderService  {
    fun onCreate() {        
        val s = 1;
        inlineCall {
            {  //wrong inner class 
 //public final static INNERCLASS de/ph1b/audiobook/playback/BookReaderService$onCreate$1$1$1 null null
                val p = 1;
                {                    
                    s;
                }()
            }()
        }
    }


}

inline fun inlineCall(s: () -> Unit) {
    s()
}
