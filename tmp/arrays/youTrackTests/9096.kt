// Original bug: KT-14540

var v1: Any = Unit
var v2: Any = Unit

// Then eventually v1 and/or v2 get new values 
// and one of them "old" is instance of kotlin.Unit 
// and another one is kotlin.Unit created by Gson
// Simple `v1 == v2` will give false!
