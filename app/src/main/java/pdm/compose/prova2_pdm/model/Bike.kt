package pdm.compose.prova2_pdm.model

data class Bike (
    val codigo: String = "",
    val modelo: String = "Caloi Padrao",
    val material_do_chassi: String = "Ferro Maciço",
    val aro: Int = 16,
    val preco: Double = 0.0,
    val quantidade_de_marchas: Int = 24
)