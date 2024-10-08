package id.android.skeleton.common

/**
 * Represents a language code with its associated values.
 *
 * This enum class defines language codes and provides their corresponding
 * two-letter codes and a list of alternative codes or names.
 *
 * @property code The two-letter language code (e.g., "ID", "EN").
 * @property listCode A list of alternative codes or names for the language.
 */
enum class LanguageCode(val code: String, val listCode: List<String>) {
    /**
     * Indonesian language code.
     *
     * Code: "ID"
     * Alternative codes: "ID", "in", "id", "indonesia", "Indonesia"
     */
    ID(code = "ID", listCode = listOf("ID", "in", "id", "indonesia", "Indonesia")),

    /**
     * English language code.
     *
     * Code: "EN"
     * Alternative codes: "EN", "en", "id", "english", "English"
     */
    EN(code = "EN", listCode = listOf("EN", "en", "id", "english", "English")),
}