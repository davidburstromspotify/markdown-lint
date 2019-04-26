package com.appmattus.markdown.rules

import com.appmattus.markdown.dsl.Config

class AllRules(private val config: Config) {
    private val allRules = listOf(
        BlanksAroundFencesRule(),
        BlanksAroundHeadersRule(),
        BlanksAroundListsRule(),
        CodeBlockStyleRule(),
        CommandsShowOutputRule(),
        ConsistentEmphasisStyleRule(),
        ConsistentHeaderStyleRule(),
        ConsistentTaskListMarkerStyleRule(),
        ConsistentUlStyleRule(),
        FencedCodeLanguageRule(),
        FirstHeaderH1Rule(),
        FirstLineH1Rule(),
        HeaderIncrementRule(),
        HeaderStartLeftRule(),
        HrStyleRule(),
        LineLengthRule(),
        ListIndentRule(),
        ListMarkerSpaceRule(),
        LowerCaseFilenameRule(),
        MissingLinkSchemeRule(),
        NoBareUrlsRule(),
        NoBlanksBlockquoteRule(),
        NoConsecutiveHyphensFilenameRule(),
        NoDuplicateHeaderRule(),
        NoEmphasisAsHeaderRule(),
        NoEmptyLinksRule(),
        NoHardTabsRule(),
        NoInlineHtmlRule(),
        NoMissingSpaceAtxRule(),
        NoMissingSpaceClosedAtxRule(),
        NoMultipleBlanksRule(),
        NoMultipleSpaceAtxRule(),
        NoMultipleSpaceBlockquoteRule(),
        NoMultipleSpaceClosedAtxRule(),
        NoPunctuationFilenameRule(),
        NoReversedLinksRule(),
        NoSpaceInCodeRule(),
        NoSpaceInEmphasisRule(),
        NoSpaceInLinksRule(),
        NoSurroundingHyphensFilenameRule(),
        NoTrailingPunctuationRule(),
        NoTrailingSpacesRule(),
        NoWhitespaceFilenameRule(),
        OlPrefixRule(),
        ProperNamesRule(),
        SingleH1Rule(),
        TaskListMarkerSpaceRule(),
        UlIndentRule(),
        UlStartLeftRule(),
        ValidRelativeLinksRule()
    )

    val rules: List<Rule> by lazy {
        allRules.filterNot { rule ->
            config.rules.any { it::class == rule::class }
        } + config.rules.filter {
            it.configuration.active
        }
    }
}
