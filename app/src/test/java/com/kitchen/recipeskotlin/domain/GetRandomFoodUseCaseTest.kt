package com.kitchen.recipeskotlin.domain

import com.kitchen.recipeskotlin.activities.FilterFoodActivity
import com.kitchen.recipeskotlin.data.model.ModelFilter
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Before

class GetRandomFoodUseCaseTest {

    @RelaxedMockK
    private lateinit var filterFoodActivity: FilterFoodActivity

    lateinit var getRandomFoodUseCase: ModelFilter

     @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        //getRandomFoodUseCase = ModelFilter(filterFoodActivity)
    }
}