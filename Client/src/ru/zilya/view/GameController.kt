package ru.zilya.view

import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.MouseEvent


/**
 * Контроллер главного окна
 */
class GameController(var view: GameView, var model: GameModel) : ActionListener {

    /**
     * обработчик выбора меню
     */
    override fun actionPerformed(e: ActionEvent) {
        val cmd = e.actionCommand
        if (cmd === "New game") {
            model.newGame()
        }
        if (cmd === "5 x 5") {
            model.setDimention(5, 5, 2)
        }
        if (cmd === "10 x 10") {
            model.setDimention(10, 10, 4)
        }
        if (cmd === "15 x 15") {
            model.setDimention(15, 15, 6)
        }
        if (cmd === "20 x 20") {
            model.setDimention(20, 20, 7)
        }
        if (cmd === "About") {
            println("About")
            //			Dialog dia = new Dialog(view, "test");
//			dia.setVisible(true);
//			dia.addKeyListener(new KeyAdapter() {
//				@Override
//				public void keyPressed(KeyEvent arg0) {
//					super.keyPressed(arg0);
//					if (arg0.getKeyCode() == 27) {
//						dia.setVisible(false);
//					}
//				}
//			})
        }
        if (cmd === "Exit") {
            System.exit(0)
        }
    }

    fun mousePressed(arg0: MouseEvent) {
        val field: PanelField? = view.panelPlayerOpponent
        val x = arg0.x / (field!!.width / field.getField().getWidth())
        val y = arg0.y / (field.height / field.getField().getHeight())
        if (field.getField().isBound(x, y)) {
            model.doShotByOpponent(x, y)
        }
    }

}
