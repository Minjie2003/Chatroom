import en from "@/i18n/en.js";
import ch from "@/i18n/ch.js";
import ja from "@/i18n/ja.js";

//  responsive to i18n messages
export const messages = {
    en: en,
    ch: ch,
    ja: ja
}

//  for language switching options
export default {
    languages: [
        {
            value: 'en',
            label: 'English'
        },
        {
            value: 'ch',
            label: '中文'
        },
        {
            value: 'ja',
            label: '日本語'
        }
    ]
}

