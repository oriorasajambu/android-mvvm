package id.android.skeleton.common

import com.google.common.truth.Truth.assertThat
import id.android.skeleton.common.Converter.formatCurrency
import org.junit.Test

class ConverterTest {

    @Test
    fun formatCurrency_nullInput_returnsFallbackValue() {
        val fallbackValue = "Fallback"
        val result = null.formatCurrency { fallbackValue }
        assertThat(result).isNotEmpty()
        assertThat(result).isEqualTo(fallbackValue)
    }

    @Test
    fun formatCurrency_emptyInput_returnsFallbackValue() {
        val fallbackValue = "Fallback"
        val result = "".formatCurrency { fallbackValue }
        assertThat(result).isNotEmpty()
        assertThat(result).isEqualTo(fallbackValue)
    }

    @Test
    fun formatCurrency_invalidNumberInput_returnsFallbackValue() {
        val fallbackValue = "Fallback"
        val result = "abc".formatCurrency { fallbackValue }
        assertThat(result).isNotEmpty()
        assertThat(result).isEqualTo(fallbackValue)
    }

    @Test
    fun formatCurrency_nullInput_returnsDefaultFallbackValue() {
        val expected = "-"
        val result = null.formatCurrency()
        assertThat(result).isNotEmpty()
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun formatCurrency_emptyInput_returnsDefaultFallbackValue() {
        val expected = "-"
        val result = "".formatCurrency()
        assertThat(result).isNotEmpty()
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun formatCurrency_notValid_returnsOriginalValue() {
        val expected = "a"
        val result = "a".formatCurrency()
        assertThat(result).isNotEmpty()
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun formatCurrency_validIntegerInput_formatsCorrectly() {
        val input = "1234567"
        val expected = "1,234,567"
        val result = input.formatCurrency()
        assertThat(result).isNotEmpty()
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun formatCurrency_validDecimalInput_formatsCorrectly() {
        val input = "1234567.89"
        val expected = "1,234,567" // RoundingMode.DOWN is used
        val result = input.formatCurrency()
        assertThat(result).isNotEmpty()
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun formatCurrency_validInputWithLeadingZeros_formatsCorrectly() {
        val input = "000123"
        val expected = "123"
        val result = input.formatCurrency()
        assertThat(result).isNotEmpty()
        assertThat(result).isEqualTo(expected)
    }
}