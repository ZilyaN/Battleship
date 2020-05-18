package ru.zilya.ai

import ru.zilya.logic.Field
import java.util.*

class AI(field: Field) {
    var f: Field
    var action: AIBase
    var rand: Random

    init {
        rand = Random()
        this.f = field
        action = AIRandom(this)
    }

    fun doShot(): Int {
        return action.doShot()
    }

    fun getField(): Field {
        return f
    }
}

