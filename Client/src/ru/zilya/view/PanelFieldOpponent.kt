package ru.zilya.view

import ru.zilya.logic.Cell
import ru.zilya.logic.Field
import java.awt.Color


class PanelFieldOpponent(field: Field): PanelField(field){

    override fun getColorByStateElement(state: Int): Color {
        when (state) {
            Cell.CELL_BORDER -> return Color(225, 225, 255)
            Cell.CELL_WATER -> return Color(225, 225, 255)
            Cell.CELL_WELL -> return Color(225, 225, 255)
            Cell.CELL_INJURED -> return Color.red
            Cell.CELL_KILLED -> return Color.gray
            Cell.CELL_MISSED -> return Color.black
        }
        return Color.blue
    }
}