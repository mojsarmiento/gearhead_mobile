package com.example.navigations.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT

interface ApiService {

    // Auth Register to Logout
    @POST("user/login")
    fun loginUser(@Body request: LoginRequest): Call<LoginResponse>

    @POST("user/register")
    fun registerUser(@Body registerRequest: RegisterRequest): Call<RegisterResponse>

    @POST("user/logout")
    fun logoutUser(@Body request: LogoutRequest, @Header("Authorization") token: String): Call<LogoutResponse>

    // Update Password and Profile
    @PUT("user/profile/password")
    fun updatePassword(@Header("Authorization") token: String, @Body request: ChangePasswordRequest): Call<ChangePasswordResponse>

    @PUT("user/profile")
    fun updateProfile(@Header("Authorization") token: String, @Body request: UpdateProfileRequest): Call<UpdateProfileResponse>

    // GET Motor & Accessories
    @GET("user/motor")
    fun motorUser(): Call<List<MotorResponse>>

    @GET("user/accessories")
    fun accessoriesUser(): Call<List<AccessoriesResponse>>

    // GET ORDERS Motor & Accesories
    @GET("api/user/orders")
    fun ordersUser(@Header("Authorization") token: String): Call<List<OrdersRequest>>

    @POST("cart/add")
    fun addToCart(@Body addToCartRequest: AddtoCartRequest): Call<AddtoCartResponse>
}
