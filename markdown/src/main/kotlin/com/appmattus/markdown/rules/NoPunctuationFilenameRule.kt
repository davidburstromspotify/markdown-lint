package com.appmattus.markdown.rules

import com.appmattus.markdown.dsl.RuleSetup
import com.appmattus.markdown.errors.ErrorReporter
import com.appmattus.markdown.processing.MarkdownDocument

/**
 * # Replace punctuation characters by hyphens
 *
 * Prefer to base the file name on the top-header level:
 * 1. replace upper case letters with lower case
 * 1. strip articles the, a, an from the start
 * 1. replace punctuation and white space characters by hyphens
 * 1. replace consecutive hyphens by a single hyphen
 * 1. strip surrounding hyphens
 *
 * Good:
 *
 *     file-name.md
 *
 * Bad, multiple consecutive hyphens:
 *
 *     file--name.md
 *
 * Bad, surrounding hyphens:
 *
 *     -file-name-.md
 *
 * Rationale: why not underscore or camel case? Hyphens are the most popular URL separator today, and markdown files
 * are most often used in contexts where:
 * 1. there are hyphen separated HTML files in the same project, possibly the same directory as the markdown files.
 * 1. filenames will be used directly on URLs. E.g.: GitHub blobs.
 *
 * Based on [File name](https://www.cirosantilli.com/markdown-style-guide/#file-name)
 */
class NoPunctuationFilenameRule(
    private val punctuation: String = ".,;:!?_",
    override val config: RuleSetup.Builder.() -> Unit = {}
) : Rule() {

    private val punctuationRegex = Regex("[${Regex.escape(punctuation)}]")
    private val doubleHyphenRegex = "-{2,}".toRegex()

    private val fileExtensionRegex = Regex("\\.(md|markdown)$")

    override fun visitDocument(document: MarkdownDocument, errorReporter: ErrorReporter) {
        val filename = document.filename.replace(fileExtensionRegex, "")

        if (filename.contains(punctuationRegex)) {
            val extension = document.filename.replaceFirst(filename, "")
            val replacement = filename.replace(punctuationRegex, "-").replace(doubleHyphenRegex, "-") + extension
            val description = "Filenames must not contain punctuation, for example '$replacement'. Configuration: " +
                    "punctuation=$punctuation."

            errorReporter.reportError(0, 0, description)
        }
    }
}
