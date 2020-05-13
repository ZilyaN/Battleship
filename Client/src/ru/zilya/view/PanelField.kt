package ru.zilya.view

import ru.zilya.logic.*
import ru.zilya.logic.Cell
import java.awt.Color
import java.awt.Graphics
import javax.swing.JPanel


abstract class PanelField(field: Field) : JPanel(), ISubscriber {
    private val field: Field
    fun getField(): Field {
        return field
    }

    private val cellWidth: Int
        private get() = width / getField().getWidth()

    private val cellHeight: Int
        private get() = height / getField().getHeight()

    protected abstract fun getColorByStateElement(state: Int): Color?
    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        // рисуем решётку
        for (i in 0 until getField().getWidth() + 1) {
            g.drawLine(i * cellWidth, 0, i * cellWidth, cellHeight * getField().getHeight())
        }
        for (i in 0 until getField().getHeight() + 1) {
            g.drawLine(0, i * cellHeight, cellWidth * getField().getWidth(), i * cellHeight)
        }


        // рисуем элементы
        for (j in 0 until getField().getHeight()) {
            for (i in 0 until getField().getWidth()) {
                val state: Int = field.getCell(i, j)!!.getState()
                g.color = getColorByStateElement(state)
                if (state == Cell.CELL_MISSED) {
                    g.fillRect(
                        i * cellWidth + cellWidth / 2 - 1,
                        j * cellHeight + cellHeight / 2 - 1,
                        4,
                        4
                    )
                } else {
                    g.fillRect(i * cellWidth + 1, j * cellHeight + 1, cellWidth - 1, cellHeight - 1)
                }
            }
        }
    }

    override fun update() {
        this.repaint()
    }

    init {
        this.field = field
    }
}