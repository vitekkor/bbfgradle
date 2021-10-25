// Original bug: KT-20754

    package com.example.library
    
    object Turtles {
        val allTheWayDown = "Yertle"
    }
    
    val canYouHearTheDrums: Boolean
        get() = Turtles.allTheWayDown == "Yertle"
