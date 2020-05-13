package ru.zilya.view

import ru.zilya.logic.*
import java.awt.Color
import java.awt.Graphics


class PanelFieldB(model: GameModel?) : PanelField(model) {
    private fun getColorByStateElement(state: ElementStates): Color {
        when (state) {
            ElementStates.enBorder -> return Color(225, 225, 255)
            ElementStates.enWater -> return Color(225, 225, 255)
            ElementStates.enWell -> return Color(225, 225, 255)
            ElementStates.enInjured -> return Color.red
            ElementStates.enKilled -> return Color.gray
            ElementStates.enMissed -> return Color.black
        }
        return Color.blue
    }

   protected fun paintElement(g: Graphics, i: Int, j: Int){
        val state: ElementStates = model.playerFiledB.GetElement(i, j)
        g.color = getColorByStateElement(state)
        if (state === ElementStates.enMissed) {
            g.fillRect(i * 15 + 6, j * 15 + 6, 4, 4)
        } else {
            g.fillRect(i * 15 + 1, j * 15 + 1, 14, 14)
        }
    }

    companion object {
        private const val serialVersionUID = -1770790832677981438L
    }
}
