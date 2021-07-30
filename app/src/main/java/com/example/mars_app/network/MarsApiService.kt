package com.example.mars_app.network

import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL="https://mars.udacity.com/"
private val retrofit: Retrofit = Retrofit.Builder().
addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL).build()
// Create a MarsApiService interface, and define a method to request the JSON response string.
interface MarsApiService {

    //Annotate the method with @GET that,
    // specifying the endpoint for the JSON real estate response,
    // and create the Retrofit Call object that will start the HTTP request.
    @GET("realestate") // when function call it append this get parameter to base_url end and make complete end point of web service
 suspend fun getPropertiesAsync() : Response <ArrayList<MarsProperty>> //Call ->   An invocation of a Retrofit method that sends a request to a webserver and returns a response.
    //Passing in the service API you just defined,
    // create a public object called MarsApi to expose the Retrofit service to the rest of the app:
    object MarsApi{
        val retrofitService : MarsApiService by lazy {
            retrofit.create(MarsApiService::class.java)
        }
    }

}