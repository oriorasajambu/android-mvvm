package id.android.skeleton.common

import android.os.Build
import android.view.View
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import java.util.Locale

object Utils {
    fun getOSversion() = Build.VERSION.SDK_INT.toString()

    fun getPhoneBrand() = (Build.MANUFACTURER).replaceFirstChar(Char::uppercase)

    fun getPhoneType() = Build.MODEL.map(Char::uppercase).joinToString()

    fun View.show() {
        visibility = View.VISIBLE
    }

    fun View.hide() {
        visibility = View.GONE
    }

    fun View.invisible() {
        visibility = View.INVISIBLE
    }

    fun Any?.parse(): JsonObject {
        return JsonParser.parseString(Gson().toJson(this)).asJsonObject
    }

    inline fun <I, reified O> I.convert(): O {
        return Gson().fromJson(Gson().toJson(this), object : TypeToken<O>() {}.type)
    }

    fun <T> T?.orDefault(default: T): T = this ?: default

    fun String.removeWhiteSpaces(): String = this.replace("\\s".toRegex(), "")

    fun getLanguage(): String {
        return Locale.getDefault().language
    }

    fun getLanguageCode(): LanguageCode {
        return when (getLanguage()) {
            in LanguageCode.ID.listCode -> LanguageCode.ID
            else -> LanguageCode.EN
        }
    }

    fun getStringByLanguange(idString: String?, enString: String?): String {
        return when (getLanguageCode()) {
            LanguageCode.ID -> idString.orEmpty()
            else -> enString.orEmpty()
        }
    }
}