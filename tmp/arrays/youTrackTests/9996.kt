// Original bug: KT-10344

interface INamed {
    val name: String
}

class MyNamed : INamed {
    override var name: String = ""; private set        
    // ... special logic related to 'name' ...
}
