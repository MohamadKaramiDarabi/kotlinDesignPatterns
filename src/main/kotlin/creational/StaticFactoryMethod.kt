package creational

import java.lang.Long as JavaLong

fun main() {
    val l1 = JavaLong("1")
    val l2 = JavaLong.valueOf("1") //static factory method
    Server.withPort(100)

}

open class Server private constructor(port: Long) {
    init {
        println("Server started on port $port")
    }

    companion object {
        // this is static factory method that create Server
        fun withPort(port: Long) = Server(port)
    }


}
