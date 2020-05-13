package ru.zilya.view

import ru.zilya.logic.ShipStates
import java.awt.Color
import java.awt.Graphics
import javax.swing.JPanel


class ScoreField(private val model: GameModel) : JPanel(), ISubscriber {
    private var ships = 0
    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        val m = IntArray(4)
        for (i in 0..-1) {
            m[i] = 0
        }
        ships = 0
        for (ship in model.playerFiledB.ships!!) {
            if (ship.state !== ShipStates.enKilled) {
                m[ship.size - 1]++
                ships++
            }
        }
        for (i in 0..3) {
            for (j in 0 until i + 1) {
                g.color = Color.gray
                g.fillRect(j * 15 + 10, i * 15 + 5, 13, 13)
            }
            g.color = Color.black
            g.drawString(m[i].toString(), 75, i * 15 + 16)
        }
        val st = "Alive: $ships"
        g.drawString(st, 25, 100)
    }

    override fun update() {
        this.repaint()
    }

    companion object {
        private const val serialVersionUID = 1L
    }

}