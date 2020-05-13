package ru.zilya.ai

import ru.zilya.logic.*
import java.util.*


class AIPlace(ai: AI) : AIBase(ai) {
    override fun doShot(): Int {
        var m: Int
        var n: Int
        val list: ArrayList<Cell?> = ArrayList<Cell?>()
        for (i in 0..1) {
            m = x + i * 2 - 1
            n = y
            if (ai.getField().isBound(m, n)) {
                val e: Cell? = ai.getField().getCell(m, n)
                if (e != null) {
                    if (e.isMark().not()) {
                        list.add(e)
                    }
                }
            }
            m = x
            n = y + i * 2 - 1
            if (ai.getField().isBound(m, n)) {
                val e: Cell? = ai.getField().getCell(m, n)
                if (e != null) {
                    if (!e.isMark()) {
                        list.add(e)
                    }
                }
            }
        }
        if (list.size > 0) {
            val e: Cell? = list[ai.rand.nextInt(list.size)]
            val shot: Int = e!!.doShot()
            if (shot == Field.SHUT_INJURED) {
                ai.action = AIDirection(ai)
                ai.action.setPosition(x, y)
                ai.action.setDirection(e.x - x, e.y - y)
            }
            return shot
        }
        ai.action = AIRandom(ai)
        return ai.doShot()
    }
}
