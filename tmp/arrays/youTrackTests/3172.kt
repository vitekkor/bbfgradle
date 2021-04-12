// Original bug: KT-39448

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet


fun main() {
    println("works as compiled class")
}
fun PreparedStatement.bind(params:Array<out Any>){
    //not relevant for error
}
fun <T> Connection.query(sql:String, params:Array<out Any>, action:(ResultSet)->T):T {
    return prepareStatement(sql).use {ps->
        ps.bind(params)
        action(ps.executeQuery())
    }
}
