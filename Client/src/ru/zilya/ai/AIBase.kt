package ru.zilya.ai

abstract class AIBase(protected var ai: AI) {
    protected var x = 0
    protected var y = 0
    protected var dx = 0
    protected var dy = 0
    abstract fun doShot(): Int
    fun setPosition(x: Int, y: Int) {
        this.x = x
        this.y = y
    }

    fun setDirection(x: Int, y: Int) {
        dx = x
        dy = y
    }

}