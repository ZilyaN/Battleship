package ru.zilya.view


import java.awt.Color
import java.awt.Graphics
import javax.swing.JPanel


class ScoreField(private val model: GameModel) : JPanel(), ISubscriber {
    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        val numShip = model.playerFieldOpponent.getMaxShip()
        val m = IntArray(numShip)
        for (i in 0..-1) {
            m[i] = 0
        }
        for (ship in model.playerFieldOpponent.getShips()!!) {
            if (ship.getState() != Ship.SHIP_KILLED) {
                m[ship.getSize()!! - 1]++
            }
        }
        for (i in 0 until numShip) {
            for (j in 0 until i + 1) {
                g.color = Color.gray
                g.fillRect(j * 10 + 8, i * 10 + 5, 8, 8)
            }
            g.color = Color.black
            g.drawString(m[i].toString(), 78, i * 10 + 12)
        }
        val so = model.playerFieldOpponent.getNumLiveShips()
        val sp = model.playerFieldPlayer.getNumLiveShips()
        g.drawString("My alive: $sp", 15, 100)
        g.drawString("Op alive: $so", 15, 120)
        if (sp == 0) g.drawString("YOU LOSER!", 20, 140)
        if (so == 0) g.drawString("YOU WON!", 20, 140)
    }

    override fun update() {
        this.repaint()
    }

}
