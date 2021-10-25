// Original bug: KT-20195

class Example
{
    fun func(condition: Boolean)
    {
        if (condition) {
            println("Yes");
        } else {
            print("No");
        }
    }
}
