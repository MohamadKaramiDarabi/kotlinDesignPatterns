package structural

//The main goal of the Adapter design pattern is to convert one interface to another interface.


interface USPlug {
  val hasPower: Int
}

interface EUPlug {
  val hasPower: String // TRUE or FALSE
}

interface UsbMini {
  val hasPower: Power
}

enum class Power {
  TRUE,FALSE;
}

interface UsbTypeC {
  val hasPower: Boolean
}


//Goal: bring the power value from a US power outlet to our cellphone
fun cellPhone(chargeCable: UsbTypeC) {
  if (chargeCable.hasPower) {
    println("I've Got The Power")
  } else {
    println("No power")
  }
}

fun usPowerOutlet(): USPlug = object : USPlug {
  override val hasPower: Int = 1
}

fun charger(plug: EUPlug): UsbMini = object : UsbMini {
  override val hasPower: Power = Power.valueOf(plug.hasPower)
}

//cellphone need UsbTypeC but our charger outlet is UsbMini, then we need an adapter to convert UsbMini to UsbTypeC
fun UsbMini.toUsbTypeC(): UsbTypeC {
  val hasPower = this.hasPower == Power.TRUE
  return object : UsbTypeC {
    override val hasPower: Boolean = hasPower
  }
}

//on the other hand we have power on UsPlug and charger need EuPlug, then we need other adapter
fun USPlug.toEUPlug(): EUPlug = object : EUPlug {
  override val hasPower: String = if (this@toEUPlug.hasPower == 1) "TRUE" else "FALSE"
}


fun main() {
  cellPhone(
    charger(
      usPowerOutlet().toEUPlug()
    ).toUsbTypeC()
  )
}