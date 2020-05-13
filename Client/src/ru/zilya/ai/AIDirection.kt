package ru.zilya.ai

import ru.zilya.logic.Cell
import java.util.*

class AIDirection(ai: AI) : AIBase(ai) {
    fun draw(list: ArrayList<Cell?>, i: Int, j: Int) {
        var m = x
        var n = y
        do {
            m += i
            n += j
        } while (ai.getField().isBound(m, n) && ai.getField().getCell(m, n)!!.getState() == Cell.CELL_INJURED)
        if (ai.getField().isBound(m, n)) {
            val e = ai.getField().getCell(m, n)
            if (!e!!.isMark()) {
                list.add(e)
            }
        }
    }

    override fun doShot(): Int {
        val list: ArrayList<Cell?> = ArrayList<Cell?>()
        draw(list, dx, dy)
        draw(list, -dx, -dy)
        if (list.size > 0) {
            return list[ai.rand.nextInt(list.size)]?.doShot()!!
        }
        ai.action = AIRandom(ai)
        return ai.doShot()
    }
}
