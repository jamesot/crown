/*
apply plugin: 'com.android.application'

        android {
        compileSdkVersion 24
        buildToolsVersion '25.0.0'
        defaultConfig {
        applicationId "android.sankara.com"
        minSdkVersion 15
        targetSdkVersion 24
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
        externalNativeBuild {
        cmake {
        cppFlags ""
        }
        }
        }
        buildTypes {
        release {
        minifyEnabled false
        proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
        }
        externalNativeBuild {
        cmake {
        path "CMakeLists.txt"
        }
        }
        }

        dependencies {
        compile fileTree(dir: 'libs', include: ['*.jar'])
        androidTestCompile('com.android.support.test.espresso:espresso-core:2.2.2', {
        exclude group: 'com.android.support', module: 'support-annotations'
        })
        //    compile 'com.android.support.constraint:constraint-layout:1.0.0-alpha8'
        //    compile 'com.github.developer-shivam:Diagonalify:1.1'
        compile 'com.android.support:appcompat-v7:24.2.1'
        compile 'com.android.support:design:24.2.1'
        compile 'com.jakewharton:butterknife:7.0.1'
        compile 'de.hdodenhof:circleimageview:2.1.0'
        compile 'com.github.gabrielemariotti.cards:cardslib-core:2.1.0'
        compile 'com.android.volley:volley:1.0.0'
        compile 'com.jaredrummler:material-spinner:1.1.0'
        compile 'com.android.support.constraint:constraint-layout:1.0.0-alpha8'
        compile 'com.github.dmytrodanylyk:android-morphing-button:98a4986e56'
        testCompile 'junit:junit:4.12'
        }

*/
