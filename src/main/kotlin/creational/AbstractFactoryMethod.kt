package creational



interface Property<T: Any> {
    val name: String
    val value: T
}
interface ServerConfiguration {
    val properties: List<Property<*>>
}



data class ServerConfigurationImpl(
    override val properties: List<Property<*>>
): ServerConfiguration


//this is abstract Factory
object Parser {
    fun <T: Any> property(prop: String): Property<T> {
        val (name, value) = prop.split(":")
        return when (name) {
            "port" -> IntProperty(name, value.trim().toInt()) as Property<T>
            "environment" -> StringProperty(name, value.trim()) as Property<T>
            else -> throw RuntimeException("Unknown property: $name")
        }
    }

    fun server(propertyString: List<String>): ServerConfiguration {
        return ServerConfigurationImpl(propertyString.map { property<Any>(it) })
    }
}

data class StringProperty(
    override val name: String,
    override val value: String
): Property<String>
data class IntProperty(
    override val name: String,
    override val value: Int
): Property<Int>


fun main() {
    val portProperty = Parser.property<Int>("port: 8080")
    val envProperty = Parser.property<String>("environment: production")
    println(portProperty.value)
    println(envProperty.value)
    val serverConfiguration = Parser.server(listOf(
        "port: 8080","environment: production"
    ))
    serverConfiguration.properties.forEach {

    }
    println(serverConfiguration)
}