package com.zcf.app2.bean


data class ArticleModel(
        val data: Data,
        val errorCode: Int,
        val errorMsg: String
) {
    data class Data(
            val curPage: Int,//当前页数
            val datas: List<Datas>,
            val offset: Int,
            val over: Boolean,
            val pageCount: Int,//总页数
            val size: Int,//一页的条数
            val total: Int//总条数
    ) {


        data class Datas(
                val apkLink: String,
                val author: String,//作者
                val chapterId: Int,//二级id
                val chapterName: String,//二级name
                val collect: Boolean,//是否收藏
                val courseId: Int,
                val desc: String,
                val envelopePic: String,
                val fresh: Boolean,
                val id: Int,
                val link: String,//博文地址
                val niceDate: String,//发布时间
                val origin: String,
                val projectLink: String,
                val publishTime: Long,//发布时间
                val superChapterId: Int,//一级id
                val superChapterName: String,//一级name
                val tags: List<Tag>,
                val title: String,
                val type: Int,
                val userId: Int,
                val visible: Int,
                val zan: Int
        ) {
            data class Tag(
                    val name: String,
                    val url: String
            )
        }


    }
}




