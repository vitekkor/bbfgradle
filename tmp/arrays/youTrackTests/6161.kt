// Original bug: KT-31324

// In module A.
interface FaceA {
    fun function()
}
// In module B.
interface FaceB : FaceA
// In module C.
interface FaceC : FaceB  // No IDE error, build failure.

class User {
    fun use(face: FaceB) {
        face.function() // No IDE error, build failure.
    }
}
