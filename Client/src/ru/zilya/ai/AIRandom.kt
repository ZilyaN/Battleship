package ru.zilya.ai

import ru.zilya.logic.*
import java.util.*


class AIRandom(ai: AI?) : AIBase(ai!!) {
    override fun doShot(): Int {
        val list: ArrayList<Cell?> = ArrayList<Cell?>()
        for (j in 0 until ai.getField().getWidth()) {
            for (i in 0 until ai.getField().getHeight()) {
                val e: Cell? = ai.getField().getCell(i, j)
                if (e != null) {
                    if (!e.isMark()) {
                        list.add(e)
                    }
                }
            }
        }
        if (list.size == 0) {
            return Field.SHUT_MISSED
        }
        val cell: Cell? = list[ai.rand.nextInt(list.size)]
        val shot: Int = cell!!.doShot()
        if (shot == Field.SHUT_INJURED) {
            ai.action = AIPlace(ai)
            ai.action.setPosition(cell.x, cell.y)
        }
        return shot
    }
}
