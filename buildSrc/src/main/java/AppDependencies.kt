import org.gradle.api.artifacts.dsl.DependencyHandler

object AppDependencies {
    private const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    private const val coroutinesCore = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    private const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    /* AndroidX */
    private const val appCompat = "androidx.appcompat:appcompat:${Versions.androidX}"
    private const val fragment = "androidx.fragment:fragment:${Versions.androidX}"
    private const val fragmentKTX = "androidx.fragment:fragment-ktx:${Versions.androidX}"
    private const val activityKTX = "androidx.activity:activity-ktx:${Versions.activityKtx}"

    private const val coreKTX = "androidx.core:core-ktx:${Versions.coreKtx}"

    private const val lifeCycleExtension =
        "androidx.lifecycle:lifecycle-extensions:${Versions.lifeCycleExtension}"
    private const val lifeCycleViewModelKTX =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifeCycle}"
    private const val lifeCycleLiveDataKTX =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifeCycle}"
    private const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    private const val swipRefreshLayout =
        "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefresh}"
    private const val recycleView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    private const val materialDesign = "com.google.android.material:material:${Versions.material}"

    /* Dagger-hilt */
    private const val daggerHilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    private const val daggerHiltCompiler =
        "com.google.dagger:hilt-android-compiler:${Versions.hilt}"

    /* Networking */
    private const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    private const val retrofitMock = "com.squareup.retrofit2:retrofit-mock:${Versions.retrofit}"
    private const val converterGSON = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    private const val loggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp3}"
    private const val okHttp3MockWebServerTest =
        "com.squareup.okhttp3:mockwebserver:${Versions.okhttp3}"
    private const val gson = "com.google.code.gson:gson:${Versions.gson}"

    /**
     * Tests
     */
    private const val junit = "junit:junit:${Versions.junit}"
    private const val kotlinJunit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlinJunit}"
    private const val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesTest}"

    private const val testCoreKTX = "androidx.test:core-ktx:${Versions.testCoreKtx}"
    private const val testExtJunitKTX = "androidx.test.ext:junit-ktx:${Versions.testExtJunit}"
    private const val testExtJunit = "androidx.test.ext:junit:${Versions.testExtJunit}"
    private const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"

    /* Espresso */
    private const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    private const val espressoIntents =
        "androidx.test.espresso:espresso-intents:${Versions.espresso}"
    private const val espressoIdlingResource =
        "androidx.test.espresso:espresso-idling-resource:${Versions.espresso}"
    private const val espressoContrib =
        "androidx.test.espresso:espresso-contrib:${Versions.espressoContrib}"
    private const val awaitility = "org.awaitility:awaitility:${Versions.awaitility}"

    /* Mockito */
    private const val mockitoCore = "org.mockito:mockito-core:${Versions.mockito}"
    private const val mockitoAndroid = "org.mockito:mockito-android:${Versions.mockito}"
    private const val mockitoKotlin = "org.mockito.kotlin:mockito-kotlin:${Versions.mockitoKotlin}"

    private const val jsoup = "org.jsoup:jsoup:${Versions.jsoup}"

    /* Debug only */
    private const val leakCanary =
        "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"
    private const val fragmentTesting =
        "androidx.fragment:fragment-testing:${Versions.fragmentTesting}"

    val appLibraries = arrayListOf<String>().apply {
        add(kotlinStdLib)
        add(fragment)
        add(kotlinStdLib)
        add(coroutinesCore)
        add(coroutinesAndroid)
        add(appCompat)
        add(fragmentKTX)
        add(activityKTX)
        add(coreKTX)
        add(lifeCycleExtension)
        add(lifeCycleViewModelKTX)
        add(lifeCycleLiveDataKTX)
        add(espressoContrib)
        add(espressoIdlingResource)
        add(constraintLayout)
        add(swipRefreshLayout)
        add(recycleView)
        add(materialDesign)
        add(daggerHilt)
        add(retrofit)
        add(retrofitMock)
        add(converterGSON)
        add(loggingInterceptor)
        add(okHttp3MockWebServerTest)
        add(gson)
        add(jsoup)
    }
    val kaptLibraries = arrayListOf<String>().apply {
        add(daggerHiltCompiler)
    }

    val androidTestImplementation = arrayListOf<String>().apply {
        add(mockitoCore)
        add(mockitoAndroid)
        add(espressoCore)
        add(espressoIntents)
        add(testCoreKTX)
        add(testExtJunitKTX)
        add(testExtJunit)
        add(awaitility)
    }
    val testImplementation = arrayListOf<String>().apply {
        add(mockitoKotlin)
        add(junit)
        add(kotlinJunit)
        add(coroutinesTest)
        add(robolectric)
    }
    val debugImplementation = arrayListOf<String>().apply {
        add(leakCanary)
        add(fragmentTesting)
    }
}

fun DependencyHandler.implementation(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandler.kapt(list: List<String>) {
    list.forEach { dependency ->
        add("kapt", dependency)
    }
}

fun DependencyHandler.kaptAndroidTest(list: List<String>) {
    list.forEach { dependency ->
        add("kaptAndroidTest", dependency)
    }
}

fun DependencyHandler.androidTestImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

fun DependencyHandler.testImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}

fun DependencyHandler.debugImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("debugImplementation", dependency)
    }
}



