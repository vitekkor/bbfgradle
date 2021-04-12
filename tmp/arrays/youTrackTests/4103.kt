// Original bug: KT-10662

interface FaceA
interface FaceB1 : FaceA { fun b1() }
fun useFaces(pa: FaceA?, pb: FaceB1?) {
    if (pa is FaceB1?) {
        pa?.b1()
        pa!!.b1() // Error is only here: "Unresolved reference: b1".
    }
    pb!!.b1()
}
