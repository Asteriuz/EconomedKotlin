package com.asteriuz.economed.ui.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

/**
 * A [VisualTransformation] that formats a string as a Brazilian CEP (Código de Endereçamento Postal).
 * CEP is a postal code used in Brazil.
 *
 * This transformation automatically formats the input text with the CEP pattern
 * (e.g., "12345-678") as the user types.
 */
class CepVisualTransformation : VisualTransformation {

    /**
     * Transforms the input text to a formatted CEP string.
     *
     * @param text The [AnnotatedString] representing the original input text.
     * @return A [TransformedText] with the formatted CEP string and an associated [OffsetMapping].
     */
    override fun filter(text: AnnotatedString): TransformedText {
        val rawText = text.text.filter { it.isDigit() }
        val formattedText = formatCep(rawText)
        return TransformedText(
            text = AnnotatedString(formattedText),
            offsetMapping = CepOffsetMapping(rawText, formattedText)
        )
    }

    /**
     * An [OffsetMapping] that maps cursor positions between the original unformatted text
     * and the transformed CEP-formatted text.
     */
    private class CepOffsetMapping(
        private val originalText: String,
        private val formattedText: String
    ) : OffsetMapping {

        /**
         * Maps the cursor position from the original text to the transformed CEP text.
         *
         * @param offset The cursor position in the original unformatted text.
         * @return The cursor position in the transformed CEP-formatted text.
         */
        override fun originalToTransformed(offset: Int): Int {
            val filteredOffset = offset.coerceIn(0, originalText.length)
            val rawText = originalText.take(filteredOffset)
            val maskedText = formatCep(rawText)
            return maskedText.length.coerceIn(0, formattedText.length)
        }

        /**
         * Maps the cursor position from the transformed CEP text back to the original text.
         *
         * @param offset The cursor position in the transformed CEP-formatted text.
         * @return The cursor position in the original unformatted text.
         */
        override fun transformedToOriginal(offset: Int): Int {
            val filteredOffset = offset.coerceIn(0, formattedText.length)
            val maskedText = formattedText.take(filteredOffset)
            return maskedText.replace(Regex("\\D"), "").length.coerceIn(0, originalText.length)
        }
    }
}

/**
 * Formats a given string as a CEP.
 *
 * @param cep A string of digits representing a CEP number.
 * @return The CEP string formatted as "XXXXX-XXX".
 */
private fun formatCep(cep: String): String {
    return when {
        cep.length > 5 -> "${cep.substring(0, 5)}-${cep.substring(5)}"
        else -> cep
    }
}
