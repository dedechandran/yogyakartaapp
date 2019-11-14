package com.example.androidjetpacksubmission.utils

import java.text.NumberFormat

class DataHelper {
    companion object {
        fun convertDurationToHoursAndMinute(duration: Int): String {
            val hour = duration / 60
            val hourReminder = duration % 60
            var minute = 0
            if (hourReminder != 0) {
                minute = hourReminder
            }
            var convertedDuration = "-"
            if (hour != 0) {
                convertedDuration = if (minute != 0) {
                    "${hour}h ${minute}m"
                } else {
                    "${hour}h"
                }
            } else {
                if (minute != 0) {
                    convertedDuration = "${minute}m"
                }

            }
            return convertedDuration
        }

        fun convertLanguageCode(languageCode: String): String = when (languageCode) {
            "en" -> "English"
            "ja" -> "Japanese"
            "cn" -> "Cantonese"
            else -> "-"
        }

        fun convertNominalToDollar(nominal: Long): String {
            return if (nominal == 0L) {
                "-"
            } else {
                val currencyFormat = NumberFormat.getCurrencyInstance()
                currencyFormat.minimumFractionDigits = 0
                currencyFormat.format(nominal)
            }
        }
    }
}