package com.example.ecommerceapp.presentation.carrinho

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.data.local.DatabaseProvider
import com.example.ecommerceapp.model.CarrinhoItem
import com.example.ecommerceapp.model.HistoricoCompra
import com.example.ecommerceapp.model.Produto
import com.example.ecommerceapp.presentation.usuario.UsuarioViewModel
import kotlinx.coroutines.launch

class CarrinhoViewModel(
    application: Application,
    private val usuarioViewModel: UsuarioViewModel
) : AndroidViewModel(application) {

    private val database = DatabaseProvider.getDatabase(application)
    private val historicoDao = database.historicoCompraDao()

    private val _itensCarrinho = MutableLiveData<List<CarrinhoItem>>(emptyList())
    val itensCarrinho: LiveData<List<CarrinhoItem>> = _itensCarrinho

    private val _subtotal = MutableLiveData(0.0)
    val subtotal: LiveData<Double> = _subtotal

    private val _mensagemCompra = MutableLiveData<String?>()
    val mensagemCompra: LiveData<String?> = _mensagemCompra

    fun adicionarProduto(produto: Produto) {
        val listaAtual = _itensCarrinho.value?.toMutableList() ?: mutableListOf()
        val itemExistente = listaAtual.find { it.produtoId == produto.id }

        if (itemExistente != null) {
            // ✅ Criar novo objeto ao invés de modificar
            val index = listaAtual.indexOf(itemExistente)
            listaAtual[index] = itemExistente.copy(quantidade = itemExistente.quantidade + 1)
        } else {
            listaAtual.add(
                CarrinhoItem(
                    produtoId = produto.id,
                    nome = produto.nome,
                    price = produto.preco,
                    imagem = 0,
                    quantidade = 1
                )
            )
        }

        _itensCarrinho.value = listaAtual
        calcularSubtotal()
    }

    fun removerProduto(item: CarrinhoItem) {
        val listaAtual = _itensCarrinho.value?.toMutableList() ?: return
        val itemEncontrado = listaAtual.find { it.produtoId == item.produtoId } ?: return

        if (itemEncontrado.quantidade > 1) {
            // ✅ Criar novo objeto
            val index = listaAtual.indexOf(itemEncontrado)
            listaAtual[index] = itemEncontrado.copy(quantidade = itemEncontrado.quantidade - 1)
        } else {
            listaAtual.remove(itemEncontrado)
        }

        _itensCarrinho.value = listaAtual
        calcularSubtotal()
    }

    // ✅ CORRIGIR: Criar nova lista com novo objeto
    fun aumentarQuantidade(item: CarrinhoItem) {
        val listaAtual = _itensCarrinho.value?.toMutableList() ?: return
        val itemEncontrado = listaAtual.find { it.produtoId == item.produtoId } ?: return

        val index = listaAtual.indexOf(itemEncontrado)
        listaAtual[index] = itemEncontrado.copy(quantidade = itemEncontrado.quantidade + 1)

        _itensCarrinho.value = listaAtual // Isso força a recomposição
        calcularSubtotal()
    }

    // ✅ CORRIGIR: Criar nova lista com novo objeto
    fun diminuirQuantidade(item: CarrinhoItem) {
        val listaAtual = _itensCarrinho.value?.toMutableList() ?: return
        val itemEncontrado = listaAtual.find { it.produtoId == item.produtoId } ?: return

        if (itemEncontrado.quantidade > 1) {
            val index = listaAtual.indexOf(itemEncontrado)
            listaAtual[index] = itemEncontrado.copy(quantidade = itemEncontrado.quantidade - 1)
            _itensCarrinho.value = listaAtual
            calcularSubtotal()
        } else {
            listaAtual.remove(itemEncontrado)
            _itensCarrinho.value = listaAtual
            calcularSubtotal()
        }
    }

    fun adicionarProdutoComQuantidade(produto: Produto, quantidade: Int) {
        val listaAtual = _itensCarrinho.value?.toMutableList() ?: mutableListOf()
        val itemExistente = listaAtual.find { it.produtoId == produto.id }

        if (itemExistente != null) {
            val index = listaAtual.indexOf(itemExistente)
            listaAtual[index] = itemExistente.copy(
                quantidade = itemExistente.quantidade + quantidade
            )
        } else {
            listaAtual.add(
                CarrinhoItem(
                    produtoId = produto.id,
                    nome = produto.nome,
                    price = produto.preco,
                    imagem = 0,
                    quantidade = quantidade
                )
            )
        }

        _itensCarrinho.value = listaAtual
        calcularSubtotal()
    }

    private fun calcularSubtotal() {
        _subtotal.value = _itensCarrinho.value?.sumOf { it.price * it.quantidade } ?: 0.0
    }

    fun limparCarrinho() {
        _itensCarrinho.value = emptyList()
        _subtotal.value = 0.0
    }

    fun confirmarCompra() {
        viewModelScope.launch {
            val total = (_subtotal.value ?: 0.0) + 10.0
            val itens = _itensCarrinho.value ?: emptyList()
            val saldoAtual = usuarioViewModel.saldo.value ?: 0.0
            val usuarioId = usuarioViewModel.usuarioId.value

            when {
                usuarioId == null -> {
                    _mensagemCompra.value = "Erro: usuário não identificado"
                }
                itens.isEmpty() -> {
                    _mensagemCompra.value = "Carrinho vazio!"
                }
                saldoAtual < total -> {
                    _mensagemCompra.value = "Saldo insuficiente! Recarregue sua conta."
                }
                else -> {
                    usuarioViewModel.descontarSaldo(total)

                    val compra = HistoricoCompra(
                        usuarioId = usuarioId,
                        dataCompra = System.currentTimeMillis(),
                        itens = itens,
                        total = total
                    )
                    historicoDao.inserirCompra(compra)

                    limparCarrinho()
                    _mensagemCompra.value = "Compra realizada com sucesso! ✅"
                }
            }

            _mensagemCompra.value = null
        }
    }

    suspend fun buscarHistoricoCompras(): List<HistoricoCompra> {
        val usuarioId = usuarioViewModel.usuarioId.value ?: return emptyList()
        return historicoDao.buscarComprasPorUsuario(usuarioId)
    }
}