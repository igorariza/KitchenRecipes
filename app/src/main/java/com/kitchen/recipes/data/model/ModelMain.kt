package com.kitchen.recipes.data.model

import java.io.Serializable


class ModelMain : Serializable {

    @JvmField
    var strCategory: String? = null

    @JvmField
    var strCategoryThumb: String? = null

    var strCategoryDescription: String? = null

}