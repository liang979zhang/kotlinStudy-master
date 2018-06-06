package com.zcf.app2.bean

/**
 * Created by 89667 on 2018/6/6.
 */

data class OfenData(
        val data: List<Data>,
        val errorCode: Int,
        val errorMsg: String
) {

    data class Data(
            val icon: String,
            val id: Int,
            val link: String,
            val name: String,
            val order: Int,
            val visible: Int
    )
}