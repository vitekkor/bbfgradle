// Original bug: KT-25994

inline class B constructor(val t: String = "B") {
   // this gets an error "Inline class primary constructor must have only final read-only (val) property parameter"
}
