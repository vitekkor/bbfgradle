// Original bug: KT-11847

package fragment

open class Fragment

interface IWebSocket {
    fun initialize(host: String, port: Int, username: String, password: String)

    fun connect()
}

open class Websocket : IWebSocket {
    override fun initialize(host: String, port: Int, username: String, password: String) {
        println("synchronous, init")
    }

    override fun connect() {
        println("synchronous, connect")
    }
}

open class AsyncWebsocket : IWebSocket{
    override fun initialize(host: String, port: Int, username: String, password: String) {
        println("async, init")
    }

    override fun connect() {
        println("async, connect")
    }
}

var socket: IWebSocket = AsyncWebsocket()

set(value){
    println("Changing Websocket to synchronous")
    field = value
    field.initialize("http://localhost", 8080, "Jim", "Password")
    field.connect()
}

class ListFragment : Fragment(), IWebSocket by socket {
    fun onCreate() {
        initialize("http://localhost", 8080, "Jim", "Password")
        connect()
        socket = Websocket()
        println("Expecting synchronous")
        initialize("http://localhost", 8080, "Jim", "Password")
        connect()
    }
}

fun main(args: Array<String>) {
    val fragment = ListFragment();
    fragment.onCreate()
}
