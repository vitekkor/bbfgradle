// Original bug: KT-25441

// We can generate direct access to the backing field only if the property is defined in the same source file,
// and the property is originally declared in a scope that is a parent of the usage scope
