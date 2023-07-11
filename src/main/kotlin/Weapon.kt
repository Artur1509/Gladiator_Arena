class Weapon(name: String, price: Int, value: Int): Item(name, price, value) {
    override var isWeapon = true
}