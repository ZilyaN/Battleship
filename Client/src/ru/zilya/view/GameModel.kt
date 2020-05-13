package ru.zilya.view

import ru.zilya.logic.FieldShip
import ru.zilya.logic.Robot
import java.util.*


/**
 * Представление главного окна
 */
class GameModel {
    private val listeners: ArrayList<ISubscriber> = ArrayList<ISubscriber>()
    var playerFiledA: FieldShip
    var playerFiledB: FieldShip
    var robot: Robot
    var currentPlayer = 0

    /**
     * Расставление кораблей заново
     */
    fun newGame() {
        playerFiledA.PutShip()
        playerFiledB.PutShip()
        updateSubscribers()
    }

    /**
     * Выстрел по текущему игроку
     */
    fun doShotByOpponent(x: Int, y: Int) {
        // если ходит локальный игрок
        if (currentPlayer == 0) {
            // если промахнулись
            if (playerFiledB.doShot(x, y) === false) {
                currentPlayer = 1
            }
        }

        // если ходит противник
        if (currentPlayer == 1) {
            while (robot.move());
            currentPlayer = 0
        }
        updateSubscribers()
    }

    /**
     * регистрация элементов, которые будут обновлять при перерисовки данных на форме
     */
    fun register(o: ISubscriber) {
        listeners.add(o)
        o.update()
    }

    /**
     * разрегистрация элеметов отображения
     */
    fun unRegister(o: ISubscriber) {
        listeners.remove(o)
    }

    /**
     * перерисовка данных в представлении
     */
    fun updateSubscribers() {
        val i: Iterator<ISubscriber> = listeners.iterator()
        while (i.hasNext()) {
            val o: ISubscriber = i.next() as ISubscriber
            o.update()
        }
    }

    /**
     * создание модели основной формы
     */
    init {
        playerFiledA = FieldShip()
        playerFiledB = FieldShip()
        robot = Robot(playerFiledA)
    }
}
