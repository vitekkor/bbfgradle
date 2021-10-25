// Original bug: KT-43427

class A

typealias B = A?
// metadata:
// B.underlyingType = A? (nullable)
// B.expandedType = A? (nullable)

typealias C = B
// metadata:
// B.underlyingType = B (non-nullable; that's fair)
// B.expandedType = A (non-nullable; wrong!)
