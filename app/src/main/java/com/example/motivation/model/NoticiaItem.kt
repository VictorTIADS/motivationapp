package com.example.motivation.model

data class NoticiaItem(
    val id: Int,
    val titulo: String,
    val fonte: String,
    val dataDePublicacao: String,
    val link: String,
    val resumo: String,
    val idFonte: Int,
    val descricao: String
)