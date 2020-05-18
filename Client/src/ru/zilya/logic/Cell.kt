package ru.zilya.logic

import Ship
import com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener
import ru.zilya.view.GameModel
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.JPanel

class Cell(val x: Int, val y: Int) {
    var xx = 0
    var yy = 0
    private var state = 0
    private var ship: Ship?
    private var mark: Boolean

    private val gm = GameModel.getInstance()
    var status: Int = -1
        set(value){
            field = value
           // repaint()
        }

    init {
        this.xx = x
        this.yy = y
        this.state = CELL_WATER
        this.ship = null
        mark = false

        addMouseListener(object: MouseAdapter(){
            override fun mouseClicked(e: MouseEvent?) {
                super.mouseClicked(e)
                if (gm.clickable && status == -1) {
                    gm.clickable = false
                    status = gm.clickRole
                    gm.lastSetPos = Pair(xx, yy)
                    //onClick.forEach { it.invoke(row, col) }
                }
            }
        })
    }

    companion object {
        const val CELL_WATER = 1
        const val CELL_BORDER = 2
        const val CELL_WELL = 3
        const val CELL_INJURED = 4
        const val CELL_KILLED = 5
        const val CELL_MISSED = 6
    }


    fun setState(state: Int) { this.state = state }

    fun getState(): Int { return state }

    fun isMark(): Boolean { return mark }

    fun setMark(m: Boolean) { this.mark = m }

    fun getShip(): Ship? { return ship }

    fun setShip(ship: Ship) { this.ship = ship }

    fun doShot(): Int {
        setMark(true)
        if (state == CELL_WELL) {
            setState(CELL_INJURED)
            return getShip()!!.doShot()
        } else {
            if (state == CELL_BORDER || state == CELL_WATER) {
                setState(CELL_MISSED)
            }
        }
        return Field.SHUT_MISSED
    }
}
