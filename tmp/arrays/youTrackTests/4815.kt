// Original bug: KT-33902

typealias ValidationRule2 = (String, String) -> String

// run Remove explicit type specification
fun getValidator(): ValidationRule2 = { text, eventType -> 
    "abc"
}
