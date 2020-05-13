package ru.zilya.logic

class TrigerShipCheck(ship: Ship?) : TrigerShip(ship!!) {
    override fun Ship(m: Int, n: Int): Boolean {
        val state = field.GetElement(m, n)
        return state === ElementStates.enWater
    }

    override fun Border(m: Int, n: Int): Boolean {
        val state = field.GetElement(m, n)
        return state === ElementStates.enBorder ||
                state === ElementStates.enWater ||
                state === ElementStates.enEmpty
    }
}
