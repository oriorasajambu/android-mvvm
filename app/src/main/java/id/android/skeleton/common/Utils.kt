package id.android.skeleton.common

import android.content.Intent
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement
import java.util.Locale
import kotlin.reflect.KClass

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
    fun getOSVersion() = SDK_INT.toString()

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
    fun getPhoneType() = Build.MODEL.map(Char::uppercase).joinToString(separator = "")

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
     * Parses an object into a [JsonElement].
     *
     * @return The parsed [JsonElement].
     */
    fun Any?.parse(): JsonElement {
        return Json.encodeToJsonElement(this)
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
     * Returns the [LanguageCode] based on the current language.
     *
     * @return The [LanguageCode].
     */
    fun getLanguageCode(): LanguageCode {
        return when (Locale.getDefault().language) {
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
    fun getStringByLanguage(idString: String?, enString: String?): String {
        return when (getLanguageCode()) {
            LanguageCode.ID -> idString.orEmpty()
            else -> enString.orEmpty()
        }
    }

    @Suppress("DEPRECATION")
    fun <T : Parcelable> Intent?.getParcelable(name: String, clazz: KClass<T>): T? {
        return if (SDK_INT >= 33) this?.getParcelableExtra(name, clazz.java)
        else this?.getParcelableExtra(name)
    }

    @Suppress("DEPRECATION")
    fun <T : Parcelable> Intent?.getParcelableArrayList(
        name: String,
        clazz: KClass<T>,
    ): ArrayList<T>? {
        return if (SDK_INT >= 33) this?.getParcelableArrayListExtra(name, clazz.java)
        else this?.getParcelableArrayListExtra(name)
    }

    @Suppress("DEPRECATION")
    fun <T : Parcelable> Bundle?.getParcelable(name: String, clazz: KClass<T>): T? {
        return if (SDK_INT >= 33) this?.getParcelable(name, clazz.java)
        else this?.getParcelable(name)
    }

    @Suppress("DEPRECATION")
    fun <T : Parcelable> Bundle?.getParcelableArrayList(
        name: String,
        clazz: KClass<T>,
    ): ArrayList<T>? {
        return if (SDK_INT >= 33) this?.getParcelableArrayList(name, clazz.java)
        else this?.getParcelableArrayList(name)
    }
}