// Original bug: KT-34975

fun test() = sequence {
    sequence {
        sequence {
            sequence {
                sequence {
                    sequence {
                        sequence {
                            sequence {
                                sequence {
                                    sequence {
                                        foo()
                                        foo()
                                        foo()
                                        foo()
                                        foo()
                                        foo()
                                        foo()
                                        foo()
                                        foo()
                                        foo()
                                        foo()
                                        yield("")
                                    }
                                    yield("")
                                }
                                yield("")
                            }
                            yield("")
                        }
                        yield("")
                    }
                    yield("")
                }
                yield("")
            }
            yield("")
        }    
        yield("")
    }
    yield("")
}

fun foo() {}
