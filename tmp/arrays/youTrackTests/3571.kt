// Original bug: KT-38588

fun fooA():Boolean
{
    print("fooA\n") // side effects
    return true;
}

fun fooB():Boolean
{
    print("fooB\n") // side effects
    return true;
}

fun main()
{
    var x=false;
    x = x || fooA();
    x = x || fooB();

    print(x);
}
