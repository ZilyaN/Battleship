package ru.zilya.view

import java.awt.Graphics
import javax.swing.JPanel


abstract class PanelField(var model: GameModel?) : JPanel(), ISubscriber {
    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        // рисуем решётку
        for (i in 0..10) {
            g.drawLine(i * 15, 0, i * 15, 150)
            g.drawLine(0, i * 15, 150, i * 15)
        }

        // рисуем элементы
        for (j in 0..9) {
            for (i in 0..9) {
                paintElement(g, i, j)
            }
        }
    }

    /**
     * рисование элементов кораблей
     */
    fun paintElement(g: Graphics?, i: Int, j: Int) {}
    override fun update() {
        this.repaint()
    }

    companion object {
        private const val serialVersionUID = 1L
    }

}
