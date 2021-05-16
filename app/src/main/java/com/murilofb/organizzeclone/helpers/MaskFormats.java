package com.murilofb.organizzeclone.helpers;

import android.widget.EditText;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

public class MaskFormats {
    public static void dateMask(EditText editText) {
        SimpleMaskFormatter smf = new SimpleMaskFormatter("NN/NN/NNNN");
        MaskTextWatcher mtw = new MaskTextWatcher(editText, smf);
        editText.addTextChangedListener(mtw);
    }
/*
    public static void moneyMask(EditText editText) {
        SimpleMaskFormatter smf = new SimpleMaskFormatter("R$ AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        MaskTextWatcher mtw = new MaskTextWatcher(editText, smf);
        editText.addTextChangedListener(mtw);
    }



    public static double removeMoneyMask(String text) {
        String[] splited = text.split("R$ ");
        return Double.parseDouble(splited[0]);
    }

 */
}
