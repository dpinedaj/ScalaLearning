package RockJVM

object Enums extends App{

  enum Permissions {
    case READ, WRITE, EXEC, NONE
  }

  /*
  Sealed class Permissions
  * READ, WRITE, EXEC, NONE as constants

   */

  val read: Permissions = Permissions.READ


  enum PermissionsWithBits(val bits: Int) {
    case READ extends PermissionsWithBits(4) // 100
    case WRITE extends PermissionsWithBits(2) // 010
    case EXEC extends PermissionsWithBits(1) // 001
    case NONE extends PermissionsWithBits(0) // 000

    def toHex: String = Integer.toHexString(bits)
    // can create variables, don't recomment
  }
  object PermissionsWithBits {
    def fromBits(bits: Int): PermissionsWithBits =
      PermissionsWithBits.NONE
  }
  val read2: PermissionsWithBits = PermissionsWithBits.READ
  val bitstring = read2.bits
  val hexString = read2.toHex
  println(bitstring)
  println(hexString)



  // Standard API
  val first = Permissions.READ.ordinal
  val allPermissions = Permissions.values // Array[Permissions]
  allPermissions.foreach(println)
  val readPermissions = Permissions.valueOf("READ") // Permissions.READ

}
