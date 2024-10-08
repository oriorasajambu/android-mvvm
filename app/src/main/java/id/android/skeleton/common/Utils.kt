package id.android.skeleton.common

import android.os.Build
import android.view.View
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import java.util.Locale

/**
 * Provides utility functions for various tasks.
 *
 * This object contains helper functions for retrieving device information,
 * manipulating views, parsing JSON, converting objects, handling null values,
 * removing whitespaces from strings, getting language information, and
 * retrieving strings based on language.
 */
object Utils {

    /**
     * Returns the OS version as a string.
     *
     * @return The OS version.
     */
    fun getOSversion() = Build.VERSION.SDK_INT.toString()

    /**
     * Returns the phone brand with the first character uppercase.
     *
     * @return The phone brand.
     */
    fun getPhoneBrand() = (Build.MANUFACTURER).replaceFirstChar(Char::uppercase)

    /**
     * Returns the phone type with all characters uppercase.
     *
     * @return The phone type.
     */
    fun getPhoneType() = Build.MODEL.map(Char::uppercase).joinToString()

    /**
     * Sets the visibility of the View to [View.VISIBLE].
     */
    fun View.show() {
        visibility = View.VISIBLE
    }

    /**
     * Sets the visibility of the View to [View.GONE].
     */
    fun View.hide() {
        visibility = View.GONE
    }

    /**
     * Sets the visibility of the View to [View.INVISIBLE].
     */
    fun View.invisible() {
        visibility = View.INVISIBLE
    }

    /**
     * Parses an object into a [JsonObject].
     *
     * @return The parsed [JsonObject].
     */
    fun Any?.parse(): JsonObject {
        return JsonParser.parseString(Gson().toJson(this)).asJsonObject
    }

    /**
     * Converts an object of type [I] to type [O].
     *
     * @return The converted object of type [O].
     */
    inline fun <I, reified O> I.convert(): O {
        return Gson().fromJson(Gson().toJson(this), object : TypeToken<O>() {}.type)
    }

    /**
     * Returns the object if not null, otherwise returns the default value.
     *
     * @return The object or the default value.
     */
    fun <T> T?.orDefault(default: T): T = this ?: default

    /**
     * Removes all whitespaces from a string.
     *
     * @return The string without whitespaces.
     */
    fun String.removeWhiteSpaces(): String = this.replace("\\s".toRegex(), "")

    /**
     * Returns the current language code.
     *
     * @return The language code.
     */
    fun getLanguage(): String {
        return Locale.getDefault().language
    }

    /**
     * Returns the [LanguageCode] based on the current language.
     *
     * @return The [LanguageCode].
     */
    fun getLanguageCode(): LanguageCode {
        return when (getLanguage()) {
            in LanguageCode.ID.listCode -> LanguageCode.ID
            else -> LanguageCode.EN
        }
    }

    /**
     * Returns the string based on the current language.
     *
     * @param idString The string in Indonesian.
     * @param enString The string in English.
     * @return The string based on the current language.
     */
    fun getStringByLanguange(idString: String?, enString: String?): String {
        return when (getLanguageCode()) {
            LanguageCode.ID -> idString.orEmpty()
            else -> enString.orEmpty()
        }
    }
}