// Original bug: KT-44832

val foo = """
        asdf
            asdf
                asdf 
                    ${
                        "hi :)"
                    }
                asdf
            asdf
        asdf
    """
