// Original bug: KT-4885

public class LiveClient() : Client<LiveClient>()
public abstract class Client<S : Client<S>>
{
    public fun onConnect(callback: (S)->Unit)
    {
        callback(this as S)
    }
}

class Foo : Client<LiveClient>()


fun main(args : Array<String>) {
  val c = Foo()
  c.onConnect {}
}
