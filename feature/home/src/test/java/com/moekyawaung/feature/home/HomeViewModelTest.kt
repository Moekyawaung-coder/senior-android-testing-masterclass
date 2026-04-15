package com.moekyawaung.feature.home

import app.cash.turbine.test
import io.kotest.core.spec.style.StringSpec
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import com.moekyawaung.testing.MainDispatcherRule
import kotlin.test.assertEquals

class HomeViewModelTest : StringSpec({
    val dispatcher = UnconfinedTestDispatcher()
    val rule = MainDispatcherRule(dispatcher)
    val repository = mockk<HomeRepository>()

    "HomeViewModel should emit loading then success state" {
        coEvery { repository.getData() } returns listOf("Senior", "Android", "Engineer")

        val viewModel = HomeViewModel(repository)

        viewModel.uiState.test {
            assertEquals(UiState.Loading, awaitItem())
            assertEquals(UiState.Success(listOf("Senior", "Android", "Engineer")), awaitItem())
        }
    
}
})
