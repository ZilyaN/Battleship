package ru.zilya.view

import ru.zilya.logic.ElementStates
import java.awt.Color
import java.awt.Graphics


open class PanelFieldA(model: GameModel?) : PanelField(model) {
    private fun getColorByStateElement(state: ElementStates): Color {
        when (state) {
            ElementStates.enBorder -> return Color(215, 215, 255)
            ElementStates.enWater -> return Color(225, 225, 255)
            ElementStates.enWell -> return Color.green
            ElementStates.enInjured -> return Color.red
            ElementStates.enKilled -> return Color.gray
            ElementStates.enMissed -> return Color.black
        }
        return Color.blue
    }

     protected fun paintElement(g: Graphics, i: Int, j: Int) {
        val state = model.playerFiledA.GetElement(i, j)
        g.color = getColorByStateElement(state)
        if (state === ElementStates.enMissed) {
            g.fillRect(i * 15 + 6, j * 15 + 6, 4, 4)
        } else {
            g.fillRect(i * 15 + 1, j * 15 + 1, 14, 14)
        }
    }

    companion object {
        private const val serialVersionUID = 553977695177508456L
    }
}

