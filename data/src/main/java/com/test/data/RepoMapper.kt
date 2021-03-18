package com.test.data

import com.test.data.model.ReposResponseData
import com.test.domain.ReposResponseModel

fun ReposResponseData.map() : ReposResponseModel{
    return ReposResponseModel(id, name, dateUpdated)
}