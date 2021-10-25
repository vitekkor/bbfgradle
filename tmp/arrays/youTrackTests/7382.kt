// Original bug: KT-26525

interface ResolveStrategy<E, K, N> where N : HasKey<E, K>, E: Entity, N: Node<E> {
// Y = works already      Y  Y  Y        XxxxXXXXXXxXxxXxxxXxxXXXXXXxxXxxXXXXXXX
// Expected swap                                    1111                     ^ ^ because there's only one generic param here, there's no confusion what a move would do anyway
// Expected swap                         22222222222    2222222222222
// Expected swap                                          3333333333333333333333
// Swap 1                                           E<>K
// Swap 2                                N<--------------->E
// Swap 3                                                  E<-------->N
}


interface Entity {

}

interface HasKey<E, K> {

}

interface Node<E: Entity> {

}


