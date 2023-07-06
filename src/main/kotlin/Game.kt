class Game {
    // Hauptattribute von Game
    var levels: MutableList<GameChar> = mutableListOf()
    var player: Player? = null

    // Skillpunkte zur einmaligen Vergabe bei der erstellung eines neuen Gladiators.
    var skillPoints = 10
}