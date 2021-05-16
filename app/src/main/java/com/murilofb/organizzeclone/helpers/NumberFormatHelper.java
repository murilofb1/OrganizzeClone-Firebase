package com.murilofb.organizzeclone.helpers;

import java.text.NumberFormat;
import java.util.Locale;

public class NumberFormatHelper {
    private final static String moedaLingua = "pt";
    private final static String moedaPais = "BR";

    public static String formatToCurrency(double number) {
        NumberFormat moeda = NumberFormat.getCurrencyInstance(new Locale(moedaLingua, moedaPais));
        String valorConvertido = moeda.format(number);
        return valorConvertido;
    }

    public static String twoDigitFormat(int number) {
        String newString = String.format("%02d", number);
        return newString;
    }
}
