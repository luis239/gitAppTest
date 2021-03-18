package com.test.remote

import android.webkit.CookieManager
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class ApiGitImpl {

    fun getApi() : ApiGit{
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(makeOkHttpClient(loggingInterceptor(BuildConfig.DEBUG)))
            .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().add(
                KotlinJsonAdapterFactory()
            ).build()))
            .build()

        return retrofit.create(ApiGit::class.java)
    }

    private fun loggingInterceptor(isDebug: Boolean) = HttpLoggingInterceptor()
        .apply {
            level = if (isDebug) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

    private fun makeOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .cookieJar(object : CookieJar {
                override fun loadForRequest(url: HttpUrl): List<Cookie> {
                    val cookieManager: CookieManager = CookieManager.getInstance()
                    val cookies: MutableList<Cookie> = ArrayList()
                    if (cookieManager.getCookie(url.toString()) != null) {
                        val splitCookies: List<String> =
                            cookieManager.getCookie(url.toString()).split(";")
                        for (i in splitCookies.indices) {
                            cookies.add(Cookie.parse(url, splitCookies[i].trim { it <= ' ' })!!)
                        }
                    }
                    return cookies
                }

                override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>)  {

                }
            })
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }


}