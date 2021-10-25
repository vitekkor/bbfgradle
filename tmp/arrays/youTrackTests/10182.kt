// Original bug: KT-2295

public fun foo(c: Collection<Int>): String =
    synchronized(c)
    {
        for(v in c)
        {
            if(v == 2)
                return "Contains value 2" // Compiler says: 'return' is not allowed here
        }

        return "Done" // Compiler says: 'return' is not allowed here
    }
