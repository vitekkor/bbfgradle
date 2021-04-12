// Original bug: KT-3316

import java.util.ArrayList

public data class AirportCode (public var code : String, public var name : String)

public class AirportCodeBuilder(){
    val list : ArrayList<AirportCode> = ArrayList<AirportCode>()

    var item : AirportCode? = null

    public fun setCode(code : String){
        item?.code = code
    }

    public fun setName(airportName : String){
        item?.name = airportName
    }

    public fun startItem(){
        item = AirportCode("","")
    }

    public fun endItem(){
        list.add(item!!)
    }
}

