package com.app.aadhi.network.service;

import com.app.aadhi.network.response.LoginResponse;
import com.app.aadhi.network.response.RegistrationResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface UserService {

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> login(@Field("email") String username, @Field("password") String password, @Field("fcm_access_token") String fcm_token);

    @FormUrlEncoded
    @POST("register")
    Call<RegistrationResponse> register(@Field("email") String username, @Field("name") String name, @Field("password") String password);

   /* @FormUrlEncoded
    @POST("create_room")
    Call<CreateRoomResponse> createRoom(@Field("user_id") String username, @Field("room_name") String roomName);

    @GET("view_rooms")
    Call<List<ViewRoomResponse>> getRooms();

    @FormUrlEncoded
    @POST("join_room")
    Call<JoinRoomResponse> joinRoom(@Field("user_id") String username, @Field("room_name") String roomName);*/

}
