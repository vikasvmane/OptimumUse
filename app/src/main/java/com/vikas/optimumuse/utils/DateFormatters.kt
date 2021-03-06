package com.vikas.optimumuse.utils

import java.text.SimpleDateFormat
import java.util.*

object DateFormatters {

    private const val DATE_FORMAT = "dd/MM/yyyy" //In which you need put here

    fun getCurrentDateInDDMMYYYY(calendar: Calendar): String {
        val sdf = SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH)
        return sdf.format(calendar.time)
    }
}