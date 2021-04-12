// Original bug: KT-13853

fun `name with spaces`() = 1  // spaces

fun `(alert('evaluate me'))`() = 2   // strange characters, executable js

fun catch() = 3  // identifier and JS reserved word clash
