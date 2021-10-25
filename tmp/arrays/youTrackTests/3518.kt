// Original bug: KT-38353

package sample

open class P

inline fun p(block: P.() -> Unit) {
    P().visit(block)
}

inline fun <T : P> T.visit(block: T.() -> Unit) = visit1 { block() }
inline fun <T : P> T.visit1(block: T.() -> Unit) = visitTag { block() }

inline fun <T : P> T.visitTag(block: T.() -> Unit) {
//        this.block()
    try {
        this.block()
    } catch (err: Throwable) {
    }
}

class Foo {

    fun foo() {
        p {
            p {
                p {
                    p {
                        p {
                            p {
                                p {
                                    p {
                                        p {
                                            p {
                                                p {
                                                    p {
                                                        p {
                                                            p {
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            p {
                p {
                    p {
                        p {
                            p {
                                p {
                                    p {
                                        p {
                                            p {
                                                p {
                                                    p {
                                                        p {
                                                            p {
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    fun didntFinishInOneMinute() {
        p {
            p {
                p {
                    p {
                        p {
                            p {
                                p {
                                    p {
                                        p {
                                            p {
                                            }
                                        }
                                    }
                                }
                                p {
                                    p {
                                        p {
                                            p {
                                            }
                                        }
                                    }
                                }
                                p {
                                    p {
                                        p {
                                            p {
                                            }
                                        }
                                    }
                                }
                                p {
                                    p {
                                        p {
                                            p {
                                            }
                                        }
                                    }
                                }
                                p {
                                    p {
                                        p {
                                            p {
                                            }
                                        }
                                    }
                                }
                                p {
                                    p {
                                        p {
                                            p {
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
