// Original bug: KT-32565

    fun findCredentials(): Pair<String, String>? = TODO()

    fun test() {
        val data = run {
            val credentials = findCredentials()
            
            object {
                val foundCredentials = credentials    
            }
        }
        
        println(data.foundCredentials)
    }
