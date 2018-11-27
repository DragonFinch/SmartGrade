package com.school.nfcard.api


import com.school.nfcard.entity.*

import okhttp3.ResponseBody
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url
import rx.Observable

/**
 * 此类的作用：接口地址
 *
 *
 * Created by Liu on 2018/6/11.
 */
interface ApiService {

    @get:POST("index/apki")
    val appVertion: Observable<AppVersion>

    @FormUrlEncoded
    @POST("Api/Comeout/pullData")
    fun getContact(@Field("c_token") c_token: String, @Field("type") type: String): Observable<BaseListModle<Student>>


    @FormUrlEncoded
    @POST("Api/Comeout/changeStatus")
    fun changeStatus(@Field("type") type: String): Observable<String>


    @FormUrlEncoded
    @POST("Api/Comeout/inoutschool")
    fun inoutschool(@Field("c_token") c_token: String, @Field("studentid") studentid: String,
                    @Field("student_name") student_name: String, @Field("comeout") comeout: String,
                    @Field("parents") parents: String, @Field("logo") logo: String): Observable<BaseModle>


    @FormUrlEncoded
    @POST("lessonlogin/login")
    fun login(@Field("phone") phone: String, @Field("password") password: String): Observable<LoginRes>


    @FormUrlEncoded
    @POST("lessonlogin/add")
    fun bindingDevice(@Field("schoolid") schoolid: String, @Field("classid") classid: String, @Field("equipmentid") equipmentid: String): Observable<Binding>


    @FormUrlEncoded
    @POST("lesson/equipment")
    fun getLesson(@Field("equipmentid") equipment: String): Observable<Lesson>


    @FormUrlEncoded
    @POST("lesson/idcard")
    fun swipecard(@Field("idcard") card: String, @Field("headLogo") head: String): Observable<SwipeCard>

    @GET
    fun downFile(@Url fileUrl: String): Observable<ResponseBody>


    @FormUrlEncoded
    @POST("lessonlogin/grade_content")
    fun getclassContent(@Field("classid") card: String): Observable<ClassContent>


    @FormUrlEncoded
    @POST("lessonlogin/school_content")
    fun getSchoolContent(@Field("classid") card: String): Observable<SchoolContent>


    @FormUrlEncoded
    @POST("lessonlogin/class_dynamics")
    fun getClassDynamics(@Field("classid") card: String): Observable<DynamicsBase>


    @FormUrlEncoded
    @POST("lessonlogin/class_play")
    fun getClassEvent(@Field("classid") card: String): Observable<ClassEvent>

}
