package com.example.timezone.data

import java.util.Date

fun fromResultToResultDataItem(result: Result): ResultDataItem {
    return ResultDataItem(
        id = result.id,
        time = result.time,
        date = result.date.time,
        success = result.success
    )

}


fun fromResultDataItemToResult(resultDataItem: ResultDataItem): Result {
    val inputDate = resultDataItem.date
    return Result(
        id = resultDataItem.id,
        time = resultDataItem.time,
        date = Date(inputDate),
        success = resultDataItem.success
    )
}



