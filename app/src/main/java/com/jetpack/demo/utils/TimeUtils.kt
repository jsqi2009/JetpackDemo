package com.jetpack.demo.utils

import java.text.SimpleDateFormat
import java.util.*

object TimeUtils {

    /**
     * @param second 秒
     * @description: 秒转换为时分秒 HH:mm:ss 格式 仅当小时数大于0时 展示HH
     * @return: [String]
     * @author: pzzhao
     * @date: 2022-05-08 13:55:17
     */
    fun second2Time(second: Long?): String {
        if (second == null || second < 0) {
            return "00:00"
        }
        val h = second / 3600
        val m = second % 3600 / 60
        val s = second % 60
        var str = ""
        if (h > 0) {
            str = (if (h < 10) "0$h" else h).toString() + ":"
        }
        str += (if (m < 10) "0$m" else m).toString() + ":"
        str += if (s < 10) "0$s" else s
        return str
    }

    /**
     * @param second 秒
     * @description: 秒转换为时分秒 HH:mm:ss 格式 仅当小时数大于0时 展示HH
     * @return: [String]
     * @author: pzzhao
     * @date: 2022-05-08 13:55:17
     */
    fun courseSecond2Time(second: Long?): String {
        if (second == null || second < 0) {
            return "0天 00:00:00"
        }
        val day =  second / 86400
        val h = second % 86400 / 3600
        val m = second % 3600 / 60
        val s = second % 60
        var str = ""

        str = (if (h < 10) "0$h" else h).toString() + ":"
        str += (if (m < 10) "0$m" else m).toString() + ":"
        str += if (s < 10) "0$s" else s
        return day.toString()+"天 "+str
    }

    fun millisecondsToTime(milliseconds: Long): String {
        val totalSeconds = milliseconds / 1000
        val hours = totalSeconds / 3600
        val minutes = (totalSeconds % 3600) / 60
        val seconds = totalSeconds % 60

        if (hours > 0) {
            return String.format("%02d 时 %02d 分 %02d 秒", hours, minutes, seconds)
        } else {
            return String.format("%02d 分 %02d 秒", minutes, seconds)
        }
    }

    fun millisecondsToTime2(milliseconds: Long): String {
        val totalSeconds = milliseconds / 1000
        val hours = totalSeconds / 3600
        val minutes = (totalSeconds % 3600) / 60
        val seconds = totalSeconds % 60

        if (hours > 0) {
            return String.format("%02d:%02d:%02d", hours, minutes, seconds)
        } else {
            return String.format("%02d:%02d", minutes, seconds)
        }
    }

    /**
     * @param birthday 日期格式yyyy-MM-dd
     * 根据用户生日计算年龄
     * 用Calender对象取得当前日期对象--从对象中分别取出年月日
     * 输出结果 例如 26岁9个月零28天
     */
    fun getAgeByBirth(birthday: String?): String {
        //从Calendar对象中或得一个Date对象
        val now = Calendar.getInstance()
        /*把出生日期放入Calendar类型的bir对象中，进行Calendar和Date类型进行转换*/
        val bir = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        bir.time = simpleDateFormat.parse(birthday)
        //如果生日大于当前日期，则抛出异常：出生日期不能大于当前日期
        require(!now.before(birthday)) { "The birthday is before Now,It's unbelievable" }
        //取出当前年月日
        val yearNow = now[Calendar.YEAR]
        val monthNow = now[Calendar.MONTH] + 1
        val dayNow = now[Calendar.DAY_OF_MONTH]
        //取出出生年月日
        val yearBirth = bir[Calendar.YEAR]
        val monthBirth = bir[Calendar.MONTH] + 1
        val dayBirth = bir[Calendar.DAY_OF_MONTH]
        //先day相减,不够向month借,然后month相减,不够向year借,最后year相减。
        var day = dayNow - dayBirth
        var month = monthNow - monthBirth
        //年龄是当前年减去出生年
        var year = yearNow - yearBirth
        if (day < 0) {
            month -= 1
            now.add(Calendar.MONTH, -1) //得到上一个月，用来得到上个月的天数。
            //借一个月
            day += now.getActualMaximum(Calendar.DAY_OF_MONTH)
        }
        if (month < 0) {
            //一年12个月,借12个月
            month = (month + 12) % 12
            //借了12个月,少一年
            year--
        }
//        val stringBuilder = StringBuilder()
//        stringBuilder.append(year.toString() + "岁")
//        stringBuilder.append(month.toString() + "月")
//        if (day < 20) {
//            stringBuilder.append("零" + day + "天")
//        } else {
//            stringBuilder.append(day.toString() + "天")
//        }
//        if (year == 0 && month == 0 && day == 0) {
//            stringBuilder.delete(0, stringBuilder.length)
//            stringBuilder.append("今日出生")
//        }
        return year.toString()
    }

    fun parseDate(dateStr: String): String {
        //需要解析的日期字符串格式
        //String dateStr = "2022-01-27 16:15:31"
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm");

        val date = format.parse(dateStr)
        val calendar = Calendar.getInstance()
        calendar.time = date

        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        return year.toString() + "年" + (month + 1).toString() + "月" + day.toString() + "日"
    }

    fun parseTime(dateStr: String): String {
        //需要解析的日期字符串格式
        //String dateStr = "2022-01-27 16:15:31"
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm");
        val format2 = SimpleDateFormat("HH:mm");

        val date = format.parse(dateStr)

        return format2.format(date)
    }

    fun string2Time(dateStr: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val outputFormat = SimpleDateFormat("MM月dd日 HH:mm", Locale.getDefault())

        val date = inputFormat.parse(dateStr)
        val formattedDate = outputFormat.format(date)

        return formattedDate
    }

    fun isToday(dateStr: String): Boolean {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val currentDate = Calendar.getInstance()

        val date = dateFormat.parse(dateStr)

        val targetDate = Calendar.getInstance()
        targetDate.time = date

        val isSameDay = (currentDate.get(Calendar.YEAR) == targetDate.get(Calendar.YEAR)
                && currentDate.get(Calendar.MONTH) == targetDate.get(Calendar.MONTH)
                && currentDate.get(Calendar.DAY_OF_MONTH) == targetDate.get(Calendar.DAY_OF_MONTH))

        return isSameDay
    }

}
