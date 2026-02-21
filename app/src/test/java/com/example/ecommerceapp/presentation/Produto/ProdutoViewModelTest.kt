package com.example.ecommerceapp.presentation.produto

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.ecommerceapp.data.repository.ProdutoRepository
import com.example.ecommerceapp.model.Produto
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ProdutoViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ProdutoViewModel
    private val repository: ProdutoRepository = mockk(relaxed = true)

    @Before
    fun setup() {
        viewModel = ProdutoViewModel(repository)
    }

    private fun fakeProducts(): List<Produto> {
        return listOf(
            Produto(id = "1", nome = "Notebook", preco = 3000.0),
            Produto(id = "2", nome = "Mouse", preco = 100.0),
            Produto(id = "3", nome = "Teclado", preco = 200.0)
        )
    }

    @Test
    fun `atualizarFiltro deve retornar todos quando busca vazia`() {
        val produtos = fakeProducts()
        viewModel.produtoList.value = produtos
        viewModel.searchQuery.value = ""

        viewModel.atualizarFiltro()

        val resultado = viewModel.filteredList.value

        assertNotNull(resultado)
        assertEquals(3, resultado!!.size)
    }

    @Test
    fun `atualizarFiltro deve filtrar corretamente`() {
        val produtos = fakeProducts()
        viewModel.produtoList.value = produtos
        viewModel.searchQuery.value = "note"

        viewModel.atualizarFiltro()

        val resultado = viewModel.filteredList.value

        assertNotNull(resultado)
        assertEquals(1, resultado!!.size)
        assertEquals("Notebook", resultado[0].nome)
    }

    @Test
    fun `atualizarFiltro deve ignorar case sensitive`() {
        val produtos = fakeProducts()
        viewModel.produtoList.value = produtos
        viewModel.searchQuery.value = "NOTE"

        viewModel.atualizarFiltro()

        val resultado = viewModel.filteredList.value

        assertNotNull(resultado)
        assertEquals(1, resultado!!.size)
    }

    @Test
    fun `atualizarFiltro deve ignorar espacos`() {
        val produtos = fakeProducts()
        viewModel.produtoList.value = produtos
        viewModel.searchQuery.value = "   mouse   "

        viewModel.atualizarFiltro()

        val resultado = viewModel.filteredList.value

        assertNotNull(resultado)
        assertEquals(1, resultado!!.size)
        assertEquals("Mouse", resultado[0].nome)
    }
}