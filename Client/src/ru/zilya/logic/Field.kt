package ru.zilya.logic

import Ship
import java.util.*


class Field(x: Int, y: Int, ship: Int) {
    private var cells: Array<Array<Cell?>>
    private var ships: ArrayList<Ship>?
    var width:Int?
    var height:Int?
    var maxShip:Int?
    var numLiveShips:Int?

    init {
        cells = emptyArray()
        ships = null
        width = 0
        height = 0
        maxShip = 0
        numLiveShips = 0
        setDimention(x, y, ship)
        setShip()
    }

    companion object {
        const val SHUT_MISSED = 1
        const val SHUT_INJURED = 2
        const val SHUT_KILLED = 3
        const val SHUT_WON = 4
    }

    fun setDimention(x: Int, y: Int, ship: Int) {
        width = x
        height = y
        maxShip = ship
    }

    fun setShip() {
        setNumLiveShips(0)
        // заполняем поле элементами воды
        // заполняем поле элементами воды
        cells = Array(getWidth()) { arrayOfNulls(getHeight()) }
        for (j in 0 until getHeight()) {
            for (i in 0 until getWidth()) {
                //TODO возможно не правильно каждый раз создавать
                cells[i][j] = Cell(i, j)
            }
        }
        // заполняем поле короблями
        // заполняем поле короблями
        ships = ArrayList()
        for (i in getMaxShip() downTo 1) {
            for (j in getMaxShip() - i + 1 downTo 1) {
                val ship = Ship(this, i)
                ships!!.add(ship)
            }
        }
        // удаляем окружение коробля
        // удаляем окружение коробля
        for (j in 0 until getHeight()) {
            for (i in 0 until getWidth()) {
                val cell = cells[i][j]!!
                if (cell.getState() == Cell.CELL_BORDER) {
                    cell.setState(Cell.CELL_WATER)
                }
            }
        }
    }

    fun doShot(x: Int, y: Int): Int {
        return getCell(x, y)?.doShot()!!
    }

    fun isBound(x: Int, y: Int): Boolean {
        return !(x < 0 || x > getWidth() - 1 || y < 0 || y > getHeight() - 1)
    }

    fun getCell(x: Int, y: Int): Cell? { return cells[x][y] }

    fun getShips(): ArrayList<Ship>? { return ships }

    fun getWidth(): Int { return width!! }

    fun setWidth(width: Int) { this.width = width }

    fun getHeight(): Int { return height!! }

    fun setHeight(height: Int) { this.height = height }

    fun getMaxShip(): Int { return maxShip!! }

    fun setMaxShip(maxShip: Int) { this.maxShip = maxShip }

    fun getNumLiveShips(): Int { return numLiveShips!! }

    fun setNumLiveShips(numLiveShips: Int) { this.numLiveShips = numLiveShips }
}
