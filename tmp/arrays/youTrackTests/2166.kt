// Original bug: KT-31799

interface Headers {
    var accept: String?
    var `content-length`: String?
}

val headers: Headers = TODO("value provided by a JS library")
val accept = headers.accept
val length = headers.`content-length`
