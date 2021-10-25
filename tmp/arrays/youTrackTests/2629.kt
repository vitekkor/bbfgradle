// Original bug: KT-40776

interface People{
    val name: String
}

class Attendee(override val name: String, val ticket: Int) : People{

}

class Speaker(override val name: String, val talks: String) : People{

}
