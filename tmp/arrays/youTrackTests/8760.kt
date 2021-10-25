// Original bug: KT-18698

val s = mutableSetOf<String>()

fun test(name: String?): Boolean {
    try {
        name?.let {
            if (s.contains(it)) {
            	s.remove(it)
     		} else {
        		s.add(it)
     		}
            
            return true
        }
        
        return false
    } finally {
        name?.hashCode()
    }
}