// Original bug: KT-15566

package test

interface HttpClient

class HttpClientImpl : HttpClient

val client by lazy {
    println("Init!")
    HttpClientImpl()
}

object DefaultHttpClient : HttpClient by client {
    val myClient = client
}

fun main(args: Array<String>) {
    DefaultHttpClient.myClient
}
