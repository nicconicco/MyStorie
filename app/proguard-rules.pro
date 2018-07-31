# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-dontusemixedcaseclassnames
 -dontskipnonpubliclibraryclasses 
-dontskipnonpubliclibraryclassmembers 
-dontpreverify
 -verbose

  -keepattributes *Annotation*,Signature,InnerClasses,EnclosingMethod 
-keepclassmembers class ** { 
    @org.greenrobot.eventbus.Subscribe <methods>;
     }
 -keep enum org.greenrobot.eventbus.ThreadMode { *; }
  -keep public class * extends android.app.Activity 
-keep public class * extends android.app.Application 
-keep public class * extends android.app.Service 
-keep public class * extends android.content.BroadcastReceiver
 -keep public class * extends android.content.ContentProvider 
-keep public class * extends android.app.backup.BackupAgentHelper 
-keep public class * extends android.preference.Preference 
-keep public class com.android.vending.licensing.ILicensingService 
-dontnote com.android.vending.licensing.ILicensingService  
-keepclassmembers enum * { 
    public static **[] values(); 
    public static ** valueOf(java.lang.String);
     }
  -keepclasseswithmembernames class * { 
    native <methods>;
     }
  -keepclassmembers class **.R$* { 
    public static <fields>; 
    }
  -keepclassmembers class * extends android.app.Activity { 
    public void *(android.view.View); 
    }


# Retrofit 2.X
## https://square.github.io/retrofit/ ##

-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

# OkHttp
-keepattributes Signature
-keepattributes *Annotation*
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**

-dontwarn okio.**
-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.** { ; }
-keep interface com.squareup.okhttp3.* { *; }
-dontwarn javax.annotation.Nullable
-dontwarn javax.annotation.ParametersAreNonnullByDefault


# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature

# For using GSON @Expose annotation
-keepattributes *Annotation*

# Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

-keep class !com.mystorie.BuildConfig, com.mystorie.** { *; }