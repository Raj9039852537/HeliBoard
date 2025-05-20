package helium314.keyboard.latin.utils;

import java.util.Locale;

import helium314.keyboard.latin.TextTransformer;
import helium314.keyboard.latin.common.StringUtils;

public enum RecapitalizeMode {
    ORIGINAL_MIXED_CASE {
        @Override
        public String apply(String text, int[] sortedSeparators, Locale locale) {
            return text;
        }
    },
    ALL_LOWER {
        @Override
        public String apply(String text, int[] sortedSeparators, Locale locale) {
            return text.toLowerCase(locale);
        }
    },
    FIRST_WORD_UPPER {
        @Override
        public String apply(String text, int[] sortedSeparators, Locale locale) {
            return StringUtils.capitalizeEachWord(text, sortedSeparators, locale);
        }
    },
    ALL_UPPER {
        @Override
        public String apply(String text, int[] sortedSeparators, Locale locale) {
            return text.toUpperCase(locale);
        }
    },
    // =========================
    // STYLE MODE (from old styke[])
    // =========================
    STYLE {
        @Override
        public String apply(String text, int[] sortedSeparators, Locale locale) {
            return sTextTransformer.transformText(text, STYLES[sStyleIndex]);
        }
    };

    // =========================
    // OLD "styke[]" ARRAY
    // =========================
    private static final String[] STYLES = {
        "low",
        "upp",
        "bold",
        "italic",
        "underline",
        "upside_down",
        "calligraphic",
        "fraktur",
        "circled",//10
        "strikethrough",
        "sub",
        "sup",
        "doublevision",
        "big",
        "wide",
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
        // ⬅ you can paste the remaining styles safely
    };

    // =========================
    // INTERNAL STATE
    // =========================
    private static final TextTransformer sTextTransformer = new TextTransformer();
    private static int sStyleIndex = 0;

    // Include STYLE in rotation carousel
    private static final RecapitalizeMode[] sCarousel = {
        ORIGINAL_MIXED_CASE,
        ALL_LOWER,
        FIRST_WORD_UPPER,
        ALL_UPPER,
        STYLE
    };

    // =========================
    // MODE DETECTION (unchanged)
    // =========================
    public static RecapitalizeMode of(final String string, final int[] sortedSeparators) {
        if (StringUtils.isIdenticalAfterUpcase(string)) {
            return ALL_UPPER;
        } else if (StringUtils.isIdenticalAfterDowncase(string)) {
            return ALL_LOWER;
        } else if (StringUtils.isIdenticalAfterCapitalizeEachWord(string, sortedSeparators)) {
            return FIRST_WORD_UPPER;
        } else {
            return ORIGINAL_MIXED_CASE;
        }
    }

    // =========================
    // ROTATION COUNT
    // =========================
    public static int count() {
        return 4 + STYLES.length;
    }

    // =========================
    // ROTATION LOGIC
    // =========================
    public final RecapitalizeMode rotate(boolean skipOriginalMixedCaseMode) {
        if (this == STYLE) {
            sStyleIndex++;
            if (sStyleIndex < STYLES.length) {
                return STYLE;
            }
            sStyleIndex = 0;
            return skipOriginalMixedCaseMode ? ALL_LOWER : ORIGINAL_MIXED_CASE;
        }

        int position = ordinal() + 1;
        if (position == sCarousel.length) {
            position = skipOriginalMixedCaseMode ? 1 : 0;
        }

        RecapitalizeMode next = sCarousel[position];
        if (next == STYLE) {
            sStyleIndex = 0;
        }
        return next;
    }

    // =========================
    // APPLY TRANSFORMATION
    // =========================
    public abstract String apply(String text, int[] sortedSeparators, Locale locale);
}
