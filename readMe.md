# Compose News App

## Overview
This sample project serves as a hands-on playground to understand the integration of Retrofit, OkHttp, and Hilt in modern Android development.
[Special Thanks : khush panchal](https://github.com/khushpanchal/NewsApp/tree/master)

<p align="center">
  <img src="https://images.template.net/148779/android-skate-animated-stickers-zyjtb.gif" style="width:30%;">
</p>

## PlayGround Branches
-  feature_retrofit_hilt -> Network calling using Retrofit and Hilt

## Implementation Details to Setup Hilt

Hilt simplifies dependency injection by automatically generating and managing dependency graphs.
3 Simple step to setup Hilt :

1. **Setup Hilt in Your Project:**
    - Add Top Project level Hilt plugin
       ```
      plugins {
        ....
        id("com.google.dagger.hilt.android") version "2.44" apply false
        }
      ```
    - Add Hilt dependencies and and hilt,kapt plugin in your `build.gradle` files
       ```
        plugins {
        ...
        id("kotlin-kapt")
        id("com.google.dagger.hilt.android")
        }
        android{...}
        kapt {
        correctErrorTypes = true
        }
        ....
        ..
        dependencies {
       implementation("com.google.dagger:hilt-android:2.44")
        kapt("com.google.dagger:hilt-android-compiler:2.44")
      ```
    - Annotate your Application class with `@HiltAndroidApp`.
       ```
      @HiltAndroidApp
        class NewsApplication : Application()  {  
        }
      ```

NOTE: make sure you Hilt gradle dependency and kotlin version sync with each other, ----------

2. **Define Hilt Modules:**
    - Create modules (using `@Module` and `@InstallIn`) to provide instances of Retrofit, OkHttpClient, and other network-related dependencies.
    - Use `@Provides` and custom qualifier annotations (like `@ApiKey` and `@BaseUrl`) to distinguish dependencies of the same type.

3. **Inject Dependencies:**
    - Use `@Inject` in constructors or fields to receive dependencies automatically.
    - For example, injecting a custom `ApiKeyInterceptor` into the OkHttpClient, or injecting the Retrofit interface into your repository or ViewModel.


## Implementation Details to Setup Retrofit

The Retrofit integration in this project involves four key steps:

1. **Setting up Dependencies and Permissions:**
    - Add Retrofit, OkHttp, and converter factory dependencies to your `build.gradle` file.
    ```
     //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.5.0")
      ```
    - Ensure that the app has the required Internet permission in the manifest:
      ```
      <uses-permission android:name="android.permission.INTERNET" />
      ```

2. **Defining the API Endpoints:**
    - Create an interface (`ApiInterface`) to define the REST API endpoints and HTTP methods (GET, POST, etc.).
    ```
     @GET("top-headlines")
   suspend fun getNews(
   @Query("country") country: String = DEFAULT_COUNTRY,
   @Query("page") pageNum: Int = DEFAULT_PAGE_NUM,
   @Query("pageSize") pageSize: Int = DEFAULT_QUERY_PAGE_SIZE,
   ): News
      ```
3. **Creating a Retrofit Instance:**
    - Build a Retrofit instance in a Hilt module.
    - Provide a base URL, a converter factory and optionally a custom-configured OkHttpClient.
    ```
    @Singleton
   @Provides
   fun provideNetworkService(
   @BaseUrl baseUrl: String,
   gsonFactory: GsonConverterFactory,
   apiKeyInterceptor: ApiKeyInterceptor
   ): ApiInterface {
   val client = OkHttpClient
   .Builder()
   .addInterceptor(apiKeyInterceptor)
   .build()

        return Retrofit
            .Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(gsonFactory)
            .build()
            .create(ApiInterface::class.java)
   }
      ```

4. **Custom OkHttpClient Configuration (Optional):**
    - Configure an OkHttpClient with custom interceptors (e.g., an interceptor for adding API keys, logging, or caching).
    - Pass this client into the Retrofit builder to ensure all network calls include the necessary configurations.



---
##  Article references:
- [Article Link: Google Official doc](https://developer.android.com/training/dependency-injection/hilt-android)
- [Article Link: Medium](https://medium.com/@ramadan123sayed/simple-guide-to-hilt-dependency-injection-in-android-with-jetpack-compose-and-ksp-3ddcbfaad37d)
