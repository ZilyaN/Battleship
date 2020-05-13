package ru.zilya.view

import ru.zilya.ai.AI
import ru.zilya.logic.Field
import java.util.*

/**
 * Представление главного окна
 */
class GameModel(dx: Int, dy: Int, numShip: Int) {
    private val listeners = ArrayList<ISubscriber>()
    var playerFieldPlayer: Field
    var playerFieldOpponent: Field
    var ai: AI
    var currentPlayer = 0
    private var enableShot = false
    fun setDimention(dx: Int, dy: Int, numShip: Int) {
        playerFieldOpponent.setWidth(dx)
        playerFieldOpponent.setHeight(dy)
        playerFieldOpponent.setMaxShip(numShip)
        playerFieldPlayer.setWidth(dx)
        playerFieldPlayer.setHeight(dy)
        playerFieldPlayer.setMaxShip(numShip)
        enableShot = true
        newGame()
        updateSubscribers()
    }

    /**
     * Расставление кораблей заново
     */
    fun newGame() {
        playerFieldPlayer.setShip()
        playerFieldOpponent.setShip()
        enableShot = true
        currentPlayer = 0
        updateSubscribers()
    }

    /**
     * Выстрел по текущему игроку
     */
    fun doShotByOpponent(x: Int, y: Int) {
        if (!enableShot) {
            return
        }
        // если ходит локальный игрок
        if (currentPlayer == 0) {
            if (playerFieldOpponent.getCell(x, y)!!.isMark()) {
                return
            }
            if (playerFieldOpponent.doShot(x, y) == Field.SHUT_MISSED) {
                // если промахнулись
                currentPlayer = 1
            }
        }
        // если ходит противник
        if (currentPlayer == 1) {
            while (ai.doShot() != Field.SHUT_MISSED);
            currentPlayer = 0
        }
        updateSubscribers()
        if (playerFieldPlayer.getNumLiveShips() == 0 || playerFieldOpponent.getNumLiveShips() == 0) {
            enableShot = false
        }
    }

    fun register(o: ISubscriber) {
        listeners.add(o)
        o.update()
    }

    fun unRegister(o: ISubscriber) {
        listeners.remove(o)
    }

    fun updateSubscribers() {
        val i: Iterator<ISubscriber> = listeners.iterator()
        while (i.hasNext()) {
            i.next().update()
        }
    }

    init {
        playerFieldPlayer = Field(dx, dy, numShip)
        playerFieldOpponent = Field(dx, dy, numShip)
        ai = AI(playerFieldPlayer)
        setDimention(dx, dy, numShip)
    }
}
