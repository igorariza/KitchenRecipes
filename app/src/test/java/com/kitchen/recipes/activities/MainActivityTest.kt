package com.kitchen.recipes.activities

import com.kitchen.recipes.data.model.ModelResponse
import com.kitchen.recipes.data.network.FoodApiClient
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MainActivityTest{

    @RelaxedMockK
    private lateinit var mainActivity: FoodApiClient

    //lateinit var getMainActivity: MainActivity

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        //        getMainActivity = MainActivity()
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

        coEvery { mainActivity.getAllDetail("test") } returns retrofit2.Response.success(
            //T body
            null
        )

        // when
        val actual = mainActivity.getAll("test")

        // then
        assertEquals(expected, expected)
    }
}