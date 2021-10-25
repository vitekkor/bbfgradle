// Original bug: KT-819

    import java.io.*

    inline val InputStream.buffered : BufferedInputStream
        get() = if(this is BufferedInputStream) this else BufferedInputStream(this)

    inline val Reader.buffered : BufferedReader
        get() = if(this is BufferedReader) this else BufferedReader(this)
