// Original bug: KT-25826

class Test {
    fun type() = "abc" //Here "Convert function to property" intention exists
     
        companion object {
           //Here I would expect coresponding intention "Convert function to value"
            fun type2() = "abc" 
        }
}
