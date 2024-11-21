/*
 * Copyright (C) 2013 The Android Open Source Project
 * modified
 * SPDX-License-Identifier: Apache-2.0 AND GPL-3.0-only
 */

package helium314.keyboard.latin.utils;

import helium314.keyboard.latin.TextTransformer;
import helium314.keyboard.keyboard.internal.keyboard_parser.KeyboardParser;
import helium314.keyboard.latin.common.StringUtils;

import java.util.Locale;

/**
 * The status of the current recapitalize process.
 */
public class RecapitalizeStatus {
    public static final int NOT_A_RECAPITALIZE_MODE = -1;
    public static final int CAPS_MODE_ORIGINAL_MIXED_CASE = 0;
    public static final int CAPS_MODE_ALL_LOWER = 1;
    public static final int CAPS_MODE_FIRST_WORD_UPPER = 2;
    public static final int CAPS_MODE_ALL_UPPER = 3;
    // When adding a new mode, don't forget to update the CAPS_MODE_LAST constant.
    public static final int CAPS_MODE_LAST = CAPS_MODE_ALL_UPPER;
    private String Transform = KeyboardParser.LAYOUT_TRANSFORMATION;

    private static final String[] styke= {
            "ori",
            "Low",
            "upp",
            "1st",
            "𝐚𝐛𝐜",
            "𝑎𝑏𝑐",
            "a̲b̲c̲",
            "ɔqɒ",
            "𝒶𝒷𝒸",
            "𝔞𝔟𝔠",
            "🅐🅑🅒",//10
            "a̶b̶c̶",
            "sub",
            "sup",
            "doublevision",
            "big",
            "a͛b͛c",
            "a≋b≋c",
            "a|b|c",
            "a.b.c",
            "a~b~c",
            "a_b_c",//20
            "a⊶b⊶c",
            "a̷b̷c",
            "a҉b҉c",
            "a⨳b⨳c",
            "a⦚b⦚c",
            "αвς∂εƒgнïʝкℓ๓ท๑ρqяรтบνพχγz",
            "Aβ⊂DξFGHไĴK£MИΘ℘QЯSTЦปฟжУζ",
            "ⓐⓑⓒⓓⓔⓕⓖⓗⓘⓙⓚⓛⓜⓝⓞⓟⓠⓡⓢⓣⓤⓥⓦⓧⓨⓩⒶⒷⒸⒹⒺⒻⒼⒽⒾⒿⓀⓁⓂⓃⓄⓅⓆⓇⓈⓉⓊⓋⓌⓍⓎⓏ",
            "ᴀʙᴄᴅᴇꜰɢʜɪᴊᴋʟᴍɴᴏᴘǫʀsᴛᴜᴠᴡxʏᴢ",
            "𝓪𝓫𝓬𝓭𝓮𝓯𝓰𝓱𝓲𝓳𝓴𝓵𝓶𝓷𝓸𝓹𝓺𝓻𝓼𝓽𝓾𝓿𝔀𝔁𝔂𝔃",
            "𝕒𝕓𝕔𝕕𝕖𝕗𝕘𝕙𝕚𝕛𝕜𝕝𝕞𝕟𝕠𝕡𝕢𝕣𝕤𝕥𝕦𝕧𝕨𝕩𝕪𝕫",//30
            "𝔞𝔟𝔠𝔡𝔢𝔣𝔤𝔥𝔦𝔧𝔨𝔩𝔪𝔫𝔬𝔭𝔮𝔯𝔰𝔱𝔲𝔳𝔴𝔵𝔶𝔷",
            "𝖆𝖇𝖈𝖉𝖊𝖋𝖌𝖍𝖎𝖏𝖐𝖑𝖒𝖓𝖔𝖕𝖖𝖗𝖘𝖙𝖚𝖛𝖜𝖝𝖞𝖟",
            "🅰🅱🅲🅳🅴🅵🅶🅷🅸🅹🅺🅻🅼🅽🅾🅿🆀🆁🆂🆃🆄🆅🆆🆇🆈🆉",
            "ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚ",
            //"‌🇦‌🇧‌🇨‌🇩‌🇪‌🇫‌🇬‌🇭‌🇮‌🇯‌🇰‌🇱‌🇲‌🇳‌🇴‌🇵‌🇶‌🇷‌🇸‌🇹‌🇺‌🇻‌🇼‌🇽‌🇾‌🇿",//
            "🄰🄱🄲🄳🄴🄵🄶🄷🄸🄹🄺🄻🄼🄽🄾🄿🅀🅁🅂🅃🅄🅅🅆🅇🅈🅉",
            "丹乃匚刀モ下ム卄工ＪＫㄥ爪れ口ㄗＱ尺ち匕∪∨山メㄚ乙",
            "ᗩᗷᑕᗪᗴᖴǤᕼᎥᒎᛕᒪᗰᑎᗝᑭɊᖇᔕ丅ᑌᐯᗯ᙭Ƴ乙",
            "ค๒ς๔єŦﻮђเןкl๓ภ๏קợгรtยשฬץאz",
            "ᏗᏰፈᎴᏋᎦᎶᏂᎥᏠᏦᏝᎷᏁᎧᎮᎤᏒᏕᏖᏬᏉᏇጀᎩፚ",//40
            "αႦƈԃҽϝɠԋιʝƙʅɱɳσρϙɾʂƚυʋɯxყȥ",
            "αв¢∂єƒgнιנкℓмησρqяѕтυνωχуz",
            "ᵃᵇᶜᵈᵉᶠᵍʰⁱʲᵏˡᵐⁿᵒᵖqʳˢᵗᵘᵛʷˣʸᶻ",
            "ᴬᴮᶜᴰᴱᶠᴳᴴᴵᴶᴷᴸᴹᴺᴼᴾQᴿˢᵀᵁⱽᵂˣʸᶻ",
            "ǟɮƈɖɛʄɢɦɨʝӄʟʍռօքզʀֆȶʊʋաӼʏʐ",
            "ɐqɔpǝɟƃɥᴉɾʞlɯuodbɹsʇnʌʍxʎz",
            "ƸYXWVUTꙄЯỌꟼOͶM⅃⋊ႱIHᎮꟻƎᗡƆᙠA",
            "ƹʏxwvuƚꙅɿpqoᴎm|ʞꞁiʜǫᎸɘbɔdɒ",
            "ꍏꌃꉓꀸꍟꎇꁅꃅꀤꀭꀘ꒒ꂵꈤꂦꉣꆰꋪꌗ꓄ꀎꃴꅏꊼꌩꁴ",
            "ąცƈɖɛʄɠɧıʝƙƖɱŋơ℘զཞʂɬų۷ῳҳყʑ",//50
            "άвςȡέғģħίјķĻмήόρqŕşţùνώxчž",
            "ค๒ς๔єŦﻮђเןкɭ๓ภ๏קợгรՇยשฬאץչ",
            "αႦƈԃҽϝɠԋιʝƙʅɱɳσρϙɾʂƚυʋɯxყȥ",
            "ǟɮƈɖɛʄɢɦɨʝӄʟʍռօքզʀֆȶʊʋաӼʏʐ",
            "ᏗᏰፈᎴᏋᎦᎶᏂᎥᏠᏦᏝᎷᏁᎧᎮᎤᏒᏕᏖᏬᏉᏇጀᎩፚ",
            "ąცƈɖɛʄɠɧıʝƙƖɱŋơ℘զཞʂɬų۷ῳҳყʑ",
            "ค๖¢໓ēfງhiวkl๓ຖ໐p๑rŞtนงຟxฯຊ",
            "αზƈԃҽϝɠԋιʝƙʅɱɳσρϙɾʂƚυʋɯxყȥ",
            "ĂβČĎĔŦĞĤĨĴĶĹМŃŐРQŔŚŤÚVŴЖŶŹ",
            "ΛϦㄈÐƐFƓнɪﾌҚŁ௱ЛØþҨ尺らŤЦƔƜχϤẔ",//60
            "ƛƁƇƊЄƑƓӇƖʆƘԼMƝƠƤƢƦƧƬƲƔƜҲƳȤ",
            "卂乃匚ᗪ乇千Ꮆ卄丨ﾌҜㄥ爪几ㄖ卩Ɋ尺丂ㄒㄩᐯ山乂ㄚ乙",
            "ﾑ乃ᄃり乇ｷムんﾉﾌズﾚﾶ刀のｱゐ尺丂ｲひ√Wﾒﾘ乙",
            "คც८ძ૯Բ૭ҺɿʆқՆɱՈ૦ƿҩՐς੮υ౮ω૪עઽ",
            "ԹՅՇԺȝԲԳɧɿʝƙʅʍՌԾρφՐՏԵՄעաՃՎՀ",
            "αɓ૮∂εƒɠɦเʝҡℓɱɳσρφ૨รƭµѵωאყƶ",
            "მჩეძპfცhἶქκlოῆõρგΓჰནυὗwჯყɀ",
            "ꋬꃳꉔ꒯ꏂꊰꍌꁝ꒐꒻ꀘ꒒ꂵꋊꄲꉣꆰꋪꇙ꓄꒤꒦ꅐꉧꌦꁴ",
            "ᵃᵇᶜᵈᵉᶠᵍʰⁱʲᵏˡᵐⁿᵒᵖqʳˢᵗᵘᵛʷˣʸᶻ",
            "абcдёfgнїjкгѫпѳpфя$тцѵщжчз",//70
            "ДБCDΞFGHIJҜLMИФPǪЯSΓЦVЩЖУZ"

};

    private static final int[] ROTATION_STYLE = new int[styke.length];
        static {
            ROTATION_STYLE[0] = CAPS_MODE_ORIGINAL_MIXED_CASE;
            ROTATION_STYLE[1] = CAPS_MODE_ALL_LOWER;
            ROTATION_STYLE[2] = CAPS_MODE_FIRST_WORD_UPPER;
            ROTATION_STYLE[3] = CAPS_MODE_ALL_UPPER;
            for (int i = 4; i < styke.length; i++) {
                ROTATION_STYLE[i] = i ;  // This will assign values starting from 3 to 70
            }
        }

    private static int getStringMode(final String string, final int[] sortedSeparators) {
        if (StringUtils.isIdenticalAfterUpcase(string)) {
            return CAPS_MODE_ALL_UPPER;
        } else if (StringUtils.isIdenticalAfterDowncase(string)) {
            return CAPS_MODE_ALL_LOWER;
        } else if (StringUtils.isIdenticalAfterCapitalizeEachWord(string, sortedSeparators)) {
            return CAPS_MODE_FIRST_WORD_UPPER;
        } else {
            return CAPS_MODE_ORIGINAL_MIXED_CASE;
        }
    }

    public static String modeToString(final int recapitalizeMode) {
        return switch (recapitalizeMode) {
            case NOT_A_RECAPITALIZE_MODE -> "undefined";
            case CAPS_MODE_ORIGINAL_MIXED_CASE -> "mixedCase";
            case CAPS_MODE_ALL_LOWER -> "allLower";
            case CAPS_MODE_FIRST_WORD_UPPER -> "firstWordUpper";
            case CAPS_MODE_ALL_UPPER -> "allUpper";
            default -> "unknown<" + recapitalizeMode + ">";
        };
    }

    /**
     * We store the location of the cursor and the string that was there before the recapitalize
     * action was done, and the location of the cursor and the string that was there after.
     */
    private int mCursorStartBefore;
    private String mStringBefore;
    private int mCursorStartAfter;
    private int mCursorEndAfter;
    private int mRotationStyleCurrentIndex;
    private boolean mSkipOriginalMixedCaseMode;
    private Locale mLocale;
    private int[] mSortedSeparators;
    private String mStringAfter;
    private boolean mIsStarted;
    private boolean mIsEnabled = true;

    private static final int[] EMPTY_STORTED_SEPARATORS = {};

    public RecapitalizeStatus() {
        // By default, initialize with dummy values that won't match any real recapitalize.
        start(-1, -1, "", Locale.getDefault(), EMPTY_STORTED_SEPARATORS);
        stop();
    }

    public void start(final int cursorStart, final int cursorEnd, final String string,
            final Locale locale, final int[] sortedSeparators) {
        if (!mIsEnabled) {
            return;
        }
        mCursorStartBefore = cursorStart;
        mStringBefore = string;
        mCursorStartAfter = cursorStart;
        mCursorEndAfter = cursorEnd;
        mStringAfter = string;
        final int initialMode = getStringMode(mStringBefore, sortedSeparators);
        mLocale = locale;
        mSortedSeparators = sortedSeparators;
        if (CAPS_MODE_ORIGINAL_MIXED_CASE == initialMode) {
            mRotationStyleCurrentIndex = 0;
            mSkipOriginalMixedCaseMode = false;
        } else {
            // Find the current mode in the array.
            int currentMode;
            for (currentMode = ROTATION_STYLE.length - 1; currentMode > 0; --currentMode) {
                if (ROTATION_STYLE[currentMode] == initialMode) {
                    break;
                }
            }
            mRotationStyleCurrentIndex = currentMode;
            mSkipOriginalMixedCaseMode = true;
        }
        mIsStarted = true;
    }

    public void stop() {
        mIsStarted = false;
    }

    public boolean isStarted() {
        return mIsStarted;
    }

    public void enable() {
        mIsEnabled = true;
    }

    public void disable() {
        mIsEnabled = false;
    }

    public boolean mIsEnabled() {
        return mIsEnabled;
    }

    public boolean isSetAt(final int cursorStart, final int cursorEnd) {
        return cursorStart == mCursorStartAfter && cursorEnd == mCursorEndAfter;
    }

    /**
     * Rotate through the different possible capitalization modes.
     */
    public void rotate() {
        final String oldResult = mStringAfter;
        TextTransformer textTransformer = new TextTransformer();
        int count = 0; // Protection against infinite loop.
        do {
            mRotationStyleCurrentIndex = (mRotationStyleCurrentIndex + 1) % ROTATION_STYLE.length;
            if (CAPS_MODE_ORIGINAL_MIXED_CASE == ROTATION_STYLE[mRotationStyleCurrentIndex]
                    && mSkipOriginalMixedCaseMode) {
                mRotationStyleCurrentIndex =
                        (mRotationStyleCurrentIndex + 1) % ROTATION_STYLE.length;
            }
            ++count;
                switch(ROTATION_STYLE[mRotationStyleCurrentIndex]){
                    case 0 -> mStringAfter = mStringBefore;
                    case 1 -> mStringAfter = mStringBefore.toLowerCase(mLocale);
                    case 2 -> mStringAfter = StringUtils.capitalizeEachWord(mStringBefore, mSortedSeparators, mLocale);
                    case 3 -> mStringAfter = mStringBefore.toUpperCase(mLocale);
                    default -> mStringAfter = textTransformer.transformText(mStringBefore,styke[mRotationStyleCurrentIndex]);
                }
       } while (mStringAfter.equals(oldResult) && count < ROTATION_STYLE.length + 1);
        mCursorEndAfter = mCursorStartAfter + mStringAfter.length();
    }

    /**
     * Remove leading/trailing whitespace from the considered string.
     */
    public void trim() {
        final int len = mStringBefore.length();
        int nonWhitespaceStart = 0;
        for (; nonWhitespaceStart < len;
                nonWhitespaceStart = mStringBefore.offsetByCodePoints(nonWhitespaceStart, 1)) {
            final int codePoint = mStringBefore.codePointAt(nonWhitespaceStart);
            if (!Character.isWhitespace(codePoint)) break;
        }
        int nonWhitespaceEnd = len;
        for (; nonWhitespaceEnd > 0;
                nonWhitespaceEnd = mStringBefore.offsetByCodePoints(nonWhitespaceEnd, -1)) {
            final int codePoint = mStringBefore.codePointBefore(nonWhitespaceEnd);
            if (!Character.isWhitespace(codePoint)) break;
        }
        // If nonWhitespaceStart >= nonWhitespaceEnd, that means the selection contained only
        // whitespace, so we leave it as is.
        if ((0 != nonWhitespaceStart || len != nonWhitespaceEnd)
                && nonWhitespaceStart < nonWhitespaceEnd) {
            mCursorEndAfter = mCursorStartBefore + nonWhitespaceEnd;
            mCursorStartBefore = mCursorStartAfter = mCursorStartBefore + nonWhitespaceStart;
            mStringAfter = mStringBefore =
                    mStringBefore.substring(nonWhitespaceStart, nonWhitespaceEnd);
        }
    }

    public String getRecapitalizedString() {
        return mStringAfter;
    }

    public int getNewCursorStart() {
        return mCursorStartAfter;
    }

    public int getNewCursorEnd() {
        return mCursorEndAfter;
    }

    public int getCurrentMode() {
        return ROTATION_STYLE[mRotationStyleCurrentIndex];
    }
}
