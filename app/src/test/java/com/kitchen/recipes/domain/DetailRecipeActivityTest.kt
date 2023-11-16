package com.kitchen.recipes.domain

import com.google.android.gms.common.api.Response
import com.kitchen.recipes.data.model.ModelResponse
import com.kitchen.recipes.data.network.FoodApiClient
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DetailRecipeActivityTest {

    @RelaxedMockK
    private lateinit var detailRecipeActivity: FoodApiClient

    //lateinit var getDetailRecipeActivity: DetailRecipeActivity

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        //        getDetailRecipeActivity = DetailRecipeActivity()
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

        coEvery { detailRecipeActivity.getAllDetail("test") } returns retrofit2.Response.success(
            //T body
            null
        )

        // when
        val actual = detailRecipeActivity.getAll("test")

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
        coEvery { detailRecipeActivity.getAllDetail("test") } returns retrofit2.Response.success(
            //T body
            null,
        )

        // when
        val actual = detailRecipeActivity.getAll("test")

        // then
        assertEquals(expected, expected)
    }
}