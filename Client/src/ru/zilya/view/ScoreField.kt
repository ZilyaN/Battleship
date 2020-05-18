package ru.zilya.view


import Ship
import java.awt.Color
import java.awt.Font
import java.awt.Graphics
import javax.swing.JPanel


class ScoreField(private val model: GameModel) : JPanel(),
    ISubscriber {
    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        val numShip = model.playerFieldOpponent.getMaxShip()
        val m = IntArray(numShip)
        for (i in 0..-1) {
            m[i] = 0
        }
        for (ship in model.playerFieldOpponent.getShips()!!) {
            if (ship.getState() != Ship.SHIP_KILLED ) {
                m[ship.getSize()!! - 1]++
                if(ship.getState() == Ship.SHIP_INJURED ){
                    m[ship.getSize()!! - 1]--
                }
            }
        }

        g.font = Font("NewRoman", Font.PLAIN, 15)
        for (i in 0 until numShip) {
            for (j in 0 until i + 1) {
                g.color = Color.gray
                g.fillRect(j * 20 + 16, i * 20 + 10, 16, 16)
            }
            g.color = Color.black
            g.drawString(m[i].toString(), 156, i * 20 + 24)

        }

        val so = model.playerFieldOpponent.getNumLiveShips()
        val sp = model.playerFieldPlayer.getNumLiveShips()
        g.drawString("My ships: $sp", 15, 170)
        g.drawString("Op ships: $so", 15, 190)
        g.font = Font("NewRoman", Font.PLAIN, 30)
        g.color = Color.RED
        if (sp == 0) g.drawString("YOU LOSE!", 5, 240)
        if (so == 0) g.drawString("YOU WON!", 20, 240)
    }

    override fun update() {
        this.repaint()
    }
}
