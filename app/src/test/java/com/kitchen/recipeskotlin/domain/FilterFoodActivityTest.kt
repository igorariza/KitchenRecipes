package com.kitchen.recipes.domain

import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Assert.*
import com.kitchen.recipes.activities.FilterFoodActivity
import com.kitchen.recipes.data.model.ModelResponse
import io.mockk.MockKAnnotations
import org.junit.Before
import org.junit.Test
import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.runBlocking
import com.kitchen.recipes.data.network.FoodApiClient
import org.jetbrains.annotations.Nullable
import retrofit2.Response

class FilterFoodActivityTest {

    @RelaxedMockK
    private lateinit var filterFoodActivity: FoodApiClient

    //lateinit var getFilterFoodActivity: FilterFoodActivity

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
//        getFilterFoodActivity = FilterFoodActivity()
    }

    @Test
    fun `when the api doesnt return anything then get values from database`() = runBlocking {
        // given
        val expected = listOf(
            ModelResponse(
                "http/1.1",
                200,
                "OK",
                "http://localhost/"
            )
        )

        coEvery { filterFoodActivity.getAll("test") } returns retrofit2.Response.success(
            //T body
            null
        )

        // when
        val actual = filterFoodActivity.getAll("test")

        // then
        assertEquals(expected, expected)
    }

    @Test
    fun `when the api return something then get values from api`() = runBlocking {
        // given
        val expected = listOf(
            ModelResponse(
                "http/1.1",
                200,
                "OK",
                "http://localhost/"
            )
        )
        coEvery { filterFoodActivity.getAll("test") } returns retrofit2.Response.success(
            //T body
            null,
        )

        // when
        val actual = filterFoodActivity.getAll("test")

        // then
        assertEquals(expected, expected)
    }
}