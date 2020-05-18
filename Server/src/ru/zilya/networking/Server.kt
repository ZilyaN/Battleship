package ru.zilya.networking

import java.net.ServerSocket
import java.net.Socket

class Server private constructor(){

    private val ss: ServerSocket
    private var stop = false
    private val connectedClient = mutableListOf<ConnectedClient>()

    companion object {
        private val PORT = 5703
        private val srv: Server =
            Server()

        fun getInstance(): Server {
            return srv
        }
    }

    abstract inner class ConnectedClient(
        val connectedClients: MutableList<ConnectedClient>,
        skt: Socket,
        val id: Int){
        private val cmn: Communicator

        init{
            cmn = Communicator(skt)
            cmn.addDataRecievedListener(::dataReceived)
            cmn.start()
            sendStatus(id%2==0)
        }

        private fun dataReceived(data: String){
            //Формат сообщений:  команда=данные
            val vls = data.split("=", limit = 2)
            if (vls.size == 2){
                when (vls[0]){
                    "pos" -> setPosition(data)
                }
            }
        }

        private fun setPosition(data: String){
            var partner: ConnectedClient? = null
            val thisInd = connectedClients.indexOf(this)
            if (id%2==0){
                if (
                    connectedClients.size>=thisInd+2 &&
                    connectedClients[thisInd+1].id%2 != 0
                )
                    partner = connectedClients[thisInd+1]
            } else {
                if (connectedClients[thisInd-1].id%2 == 0)
                    partner = connectedClients[thisInd-1]
            }
            partner?.sendPosition(data)
        }

        private fun sendPosition(data: String){
            if (cmn.isAlive){
                cmn.sendData(data)
            }
        }

        private fun sendStatus(status: Boolean) {
            if (cmn.isAlive){
                cmn.sendData("status=$status")
            }
        }
    }

    init{
        ss = ServerSocket(PORT)
        while (!stop) {
            acceptClient()
        }
    }

    private fun acceptClient() {
        println("Ожидание подключения")
        val s = ss.accept()
        println("Новый клиент подключен")
        if (s!=null) {
            val id =
                if (connectedClient.isEmpty()) 0 else connectedClient.last().id+1
            //connectedClient.add(ConnectedClient(connectedClient, s, id))
        }
    }
}