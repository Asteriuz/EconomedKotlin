package com.asteriuz.economed.ui.utils


import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

/**
 * A [VisualTransformation] that formats a string as a Brazilian CPF (Cadastro de Pessoas Físicas).
 * CPF is a tax identification number used in Brazil.
 *
 * This transformation automatically formats the input text with the CPF pattern
 * (e.g., "123.456.789-00") as the user types.
 */
class CpfVisualTransformation : VisualTransformation {

    /**
     * Transforms the input text to a formatted CPF string.
     *
     * @param text The [AnnotatedString] representing the original input text.
     * @return A [TransformedText] with the formatted CPF string and an associated [OffsetMapping].
     */
    override fun filter(text: AnnotatedString): TransformedText {
        val rawText = text.text.filter { it.isDigit() }
        val formattedText = formatCpf(rawText)
        return TransformedText(
            text = AnnotatedString(formattedText),
            offsetMapping = CpfOffsetMapping(rawText, formattedText)
        )
    }

    /**
     * An [OffsetMapping] that maps cursor positions between the original unformatted text
     * and the transformed CPF-formatted text.
     */
    private class CpfOffsetMapping(
        private val originalText: String,
        private val formattedText: String
    ) : OffsetMapping {

        /**
         * Maps the cursor position from the original text to the transformed CPF text.
         *
         * @param offset The cursor position in the original unformatted text.
         * @return The cursor position in the transformed CPF-formatted text.
         */
        override fun originalToTransformed(offset: Int): Int {
            val filteredOffset = offset.coerceIn(0, originalText.length)
            val rawText = originalText.take(filteredOffset)
            val maskedText = formatCpf(rawText)
            return maskedText.length.coerceIn(0, formattedText.length)
        }

        /**
         * Maps the cursor position from the transformed CPF text back to the original text.
         *
         * @param offset The cursor position in the transformed CPF-formatted text.
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
 * Formats a given string as a CPF.
 *
 * @param cpf A string of digits representing a CPF number.
 * @return The CPF string formatted as "XXX.XXX.XXX-XX".
 */
private fun formatCpf(cpf: String): String {
    return when {
        cpf.length > 9 -> "${cpf.substring(0, 3)}.${cpf.substring(3, 6)}.${
            cpf.substring(
                6,
                9
            )
        }-${cpf.substring(9)}"

        cpf.length > 6 -> "${cpf.substring(0, 3)}.${cpf.substring(3, 6)}.${cpf.substring(6)}"
        cpf.length > 3 -> "${cpf.substring(0, 3)}.${cpf.substring(3)}"
        else -> cpf
    }
}