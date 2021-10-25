// Original bug: KT-44622

                        package x
                     
                        inline class A(val value: Int)
                       
                        fun interface B {
                            fun method(a: A)
                        }
