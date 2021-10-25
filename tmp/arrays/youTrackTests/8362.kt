// Original bug: KT-17957

fun <T>(()->T).catch(handle: (e: Throwable)->T) : T {
    try {
        return this()
    } catch (e: Throwable) {
        return handle(e)
    }
}

fun main(args: Array<String>) {
    
    {1/0}.catch {println(it); 1};

    {1/0}.catch {println(it)} // return type will be Any
}
