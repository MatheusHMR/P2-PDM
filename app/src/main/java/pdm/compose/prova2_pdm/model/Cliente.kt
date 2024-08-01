package pdm.compose.prova2_pdm.model

data class Cliente (
    val clienteId: String = "",
    val cpf: String = "",
    val nome: String = "",
    val email: String = "",
    val instagram: String = ""
) {
    companion object {
        // Criar um cliente vazio para usar como padrão
        val EMPTY = Cliente("", "", "", "", "")
    }
}


fun Cliente.toAttributeMap() : Map<String, String> {
    return mapOf(
        "Cpf" to cpf,
        "Nome" to nome,
        "E-mail" to email,
        "Instagram" to instagram
    )
}

