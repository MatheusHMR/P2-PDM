package pdm.compose.prova2_pdm.model

data class Bike (
    val codigo: String = "",
    val modelo: String = "Caloi Padrao",
    val material_do_chassi: String = "Ferro Maciço",
    val aro: Int = 16,
    val preco: Double = 0.0,
    val quantidade_de_marchas: Int = 24,
    val clienteId: String = ""
){
    companion object {
        val EMPTY = Bike(
            "",
            "",
            "",
            0,
            0.0,
            0)
    }
}

fun Bike.toAttributeMap() : Map<String, String> {
    return mapOf(
        "Modelo" to modelo,
        "Material do Chassi" to material_do_chassi,
        "Aro" to aro.toString(),
        "Preco" to "R$ $preco",
        "Quantidade de Marchas" to quantidade_de_marchas.toString(),
        "Cliente" to clienteId
    )
}