package ru.zilya.logic

class TrigerShipSet(private val ship: Ship) : TrigerShip(ship) {
    override fun Ship(m: Int, n: Int): Boolean {
        field.SetElement(m, n, ElementStates.enWell)
        ship.elements.add(field.elements[m][n]!!)
        field.elements[m][n]!!.ship = ship
        return true
    }

    override fun Border(m: Int, n: Int): Boolean {
        field.SetElement(m, n, ElementStates.enBorder)
        return true
    }

}
