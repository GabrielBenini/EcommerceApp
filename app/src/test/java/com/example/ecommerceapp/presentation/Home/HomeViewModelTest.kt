package com.example.ecommerceapp.presentation.Home


import com.example.ecommerceapp.presentation.home.HomeContract
import com.example.ecommerceapp.presentation.home.HomeViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        viewModel = HomeViewModel()
    }

    @Test
    fun `initial state should be empty search`() = runTest {
        // When
        val initialState = viewModel.uiState.value

        // Then
        assertEquals("", initialState.searchProduct)
    }

    @Test
    fun `when OnSearchProductChange event is triggered then state should update searchProduct`() = runTest {
        // Given
        val newSearch = "Notebook"

        // When
        viewModel.handleEvent(
            HomeContract.Event.OnSearchProductChange(newSearch)
        )

        // Then
        val updatedState = viewModel.uiState.value
        assertEquals(newSearch, updatedState.searchProduct)
    }
}

