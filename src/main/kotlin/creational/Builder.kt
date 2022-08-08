package creational

import java.util.StringJoiner

class MailBuilder {

    private var to: ArrayList<String> = arrayListOf()
    private var cc: List<String> = listOf()
    private var title: String = ""
    private var message: String = ""
    private var importance: Boolean = false

    fun message(message: String) = apply {
        this.message = message
    }
    fun cc(cc: List<String>)= apply {
        this.cc = cc
    }
    fun to(to: String) = apply {
        this.to.add(to)
    }
    fun title(title: String) = apply {
        this.title = title
    }

    fun importance(importance: Boolean): MailBuilder {
        this.importance = importance
        return this
    }

    @Throws(RuntimeException::class)
    fun build(): Mail = if (to.isEmpty()) throw RuntimeException("To property is empty!!") else Mail(
        to = to,
        cc = cc.ifEmpty { null },
        message = message.ifEmpty { null },
        title = title.ifEmpty { null },
        importance = importance
    )

    data class Mail internal constructor(
        val to: List<String>,
        val cc: List<String>?,
        val title: String?,
        val message: String?,
        val importance: Boolean
    )
}

fun main() {
    val mail = MailBuilder()
        .message("message")
        .to("mohamad.karami.darabi@gmail.com")
        .to("karami_md@hotmail.com")
        .build()
    println(mail)
}