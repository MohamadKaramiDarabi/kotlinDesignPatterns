package structural

open class StarTrekRepository {
  private val startShipCaptains = mutableMapOf(
    "USS Enterprise" to "Jan-Luc Picard"
  )

  open fun getCaptain(starshipName: String): String {
    return startShipCaptains[starshipName] ?: "Unknown"
  }
  open fun addCaptain(starshipName: String, captainName: String) {
    startShipCaptains[starshipName] = captainName
  }
}


//logging is new behavior
class LoggingGetCaptainStarTrekRepository : StarTrekRepository() {
  override fun getCaptain(starshipName: String): String {
    println("Getting captain for $starshipName")
    return super.getCaptain(starshipName)
  }
}

//validation is new behavior
class ValidatingCaptainStarTrekRepository : StarTrekRepository() {
  override fun addCaptain(starshipName: String, captainName: String) {
    if (captainName.length > 15) {
      throw RuntimeException("captainName is longer than 20 characters!")
    }
    super.addCaptain(starshipName, captainName)
  }
}

//adding behavior maybe added later

//solve

interface StarTrekRepositoryInterface {
  operator fun get(starshipName: String): String
  operator fun set(starshipName: String, captainName: String)
}

class DefaultStarTrekRepository : StarTrekRepositoryInterface {
  private val startShipCaptains = mutableMapOf(
    "USS Enterprise" to "Jan-Luc Picard"
  )

  override fun get(starshipName: String): String {
    return startShipCaptains[starshipName] ?: "Unknown"
  }
  override fun set(starshipName: String, captainName: String) {
    startShipCaptains[starshipName] = captainName
  }
}

class LoggingGetCaptain(private val repositoryInterface: StarTrekRepositoryInterface) :
  StarTrekRepositoryInterface by repositoryInterface {
  override fun get(starshipName: String): String {
    println("Getting captain for $starshipName")
    return repositoryInterface[starshipName]
  }
}

class ValidatingAdd(private val repositoryInterface: StarTrekRepositoryInterface) :
  StarTrekRepositoryInterface by repositoryInterface {
  override fun set(starshipName: String, captainName: String) {
    if (captainName.length > 15) {
      throw RuntimeException("captainName is longer than 20 characters!")
    }
    repositoryInterface[starshipName]= captainName
  }
}

fun main() {
  val startTrekRepository = DefaultStarTrekRepository()
  val withValidatingAdd = ValidatingAdd(startTrekRepository)
  val withLoggingGetCaptain = LoggingGetCaptain(startTrekRepository)
  //We don't create Logging with Validating class but with delegating we can merge them together
  val withLoggingGetCaptainAndValidatingAdd = LoggingGetCaptain(withValidatingAdd)

}