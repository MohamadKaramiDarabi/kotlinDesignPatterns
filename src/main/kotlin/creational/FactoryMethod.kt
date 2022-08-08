package creational


fun main() {
    val notation = listOf("pa8", "qc3")
    val pieces = arrayListOf<ChessPiece>()
    notation.forEach {
        pieces.add(createPiece(it))
    }
    println(pieces)
}

interface ChessPiece {
    val file: Char
    val rank: Char
}

data class Pawn(
    override val file: Char,
    override val rank: Char
): ChessPiece

data class Queen(
    override val file: Char,
    override val rank: Char
): ChessPiece


//this is factory method which create object in runtime with information of that object
fun createPiece(notation: String): ChessPiece {
    val (type, file, rank) = notation.toCharArray()

    return when (type) {
        'q' -> Queen(file, rank)
        'p' -> Pawn(file, rank)
        else -> throw RuntimeException("Unknown piece: $type")
    }

}