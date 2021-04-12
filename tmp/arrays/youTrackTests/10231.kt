// Original bug: KT-2106

fun mysqlConnect(
        host: String = "localhost",
        port: Int = 3306,
        connectionTimeoutMillis: Int = -1,
        socketTimeoutMillis: Int = -1) = null


fun foo() {
    mysqlConnect("server", 3307)
    mysqlConnect("server2", socketTimeoutMillis = 3) // MIXED_NAMED_AND_POSITIONED_ARGUMENTS
}
