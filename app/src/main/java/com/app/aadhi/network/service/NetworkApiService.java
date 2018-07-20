package com.app.aadhi.network.service;

import com.app.aadhi.network.request.TestimonialRequest;
import com.app.aadhi.network.response.AudioVideoMessageDetailApi;
import com.app.aadhi.network.response.AudioVideoMessageListApi;
import com.app.aadhi.network.response.CurrentEventsDetailsApi;
import com.app.aadhi.network.response.CurrentEventsListApi;
import com.app.aadhi.network.response.EappEventsDetailsApi;
import com.app.aadhi.network.response.EappEventsListApi;
import com.app.aadhi.network.response.PilgrimageTourDetailsApi;
import com.app.aadhi.network.response.PilgrimageToursListApi;
import com.app.aadhi.network.response.PoojaDetailApi;
import com.app.aadhi.network.response.PoojaListApi;
import com.app.aadhi.network.response.SlokaOfTheDayApi;
import com.app.aadhi.network.response.SlokasMantrasDetailApi;
import com.app.aadhi.network.response.SlokasMantrasListApi;
import com.app.aadhi.network.response.TestimonialListApi;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface NetworkApiService {
    @GET("public/api/poojas")
    Call<PoojaListApi> fetchPoojaInfo();

    @GET("public/api/poojas/{id}")
    Call<PoojaDetailApi> fetchPoojaDetails(@Path("id") String id);

    @GET("public/api/tours")
    Call<PilgrimageToursListApi> fetchToursList();

    @GET("public/api/tours/{id}")
    Call<PilgrimageTourDetailsApi> fetchTourDetails(@Path("id") String id);

    @GET("public/api/audios")
    Call<AudioVideoMessageListApi> fetchAudioMessages();

    @GET("public/api/videos")
    Call<AudioVideoMessageListApi> fetchVideoMessages();

    @GET("public/api/audios/{id}")
    Observable<AudioVideoMessageDetailApi> fetchAudioMessageDetails(@Path("id") String id);

    @GET("public/api/videos/{id}")
    Call<AudioVideoMessageDetailApi> fetchVideoMessageDetails(@Path("id") String id);

    @GET("public/api/slokas")
    Observable<SlokasMantrasListApi> fetchSlokasList();

    @GET("public/api/slokas/{id}")
    Call<SlokasMantrasDetailApi> fetchSlokasWithId(@Path("id") String id);

    @GET("public/api/mantras")
    Observable<SlokasMantrasListApi> fetchMantrasList();

    @GET("public/api/mantras/{id}")
    Call<SlokasMantrasDetailApi> fetchMantrasWithId(@Path("id") String id);

    @GET("public/api/slokadays")
    Observable<SlokaOfTheDayApi> fetchSlokaOfTheDayList();

    @GET("public/api/events")
    Call<CurrentEventsListApi> fetchCurrentEventsList();

    @GET("public/api/events/{id}")
    Call<CurrentEventsDetailsApi> fetchCurrentEventDetails(@Path("id") String id);

    @GET("public/api/eappnotices")
    Call<EappEventsListApi> fetchEappEventsList();

    @GET("public/api/eappnotices/{id}")
    Call<EappEventsDetailsApi> fetchEappEventDetails(@Path("id") String id);

    @GET("public/api/testimonials")
    Observable<TestimonialListApi> fetchTestimonialInfo();

    @POST("public/api/testimonials")
    Observable<TestimonialRequest> postTestimonialInfo(@Body TestimonialRequest testimonialRequest);

}
