// Original bug: KT-41017

// TODO: design do we need casts to Nothing?
// flow.addImplication((expressionVariable eq !isEq) implies (operandVariable typeEq nullableNothing))
// flow.addImplication((expressionVariable notEq !isEq) implies (operandVariable typeNotEq nullableNothing))
