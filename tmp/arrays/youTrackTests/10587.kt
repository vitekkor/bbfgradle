// Original bug: KT-3881

    fun calc(){        
        try {
            print("x")
        } catch (e:RuntimeException) {
            print("y")
        } finally {
            print("z")
        }
    }
