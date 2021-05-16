package com.murilofb.organizzeclone.helpers;

import java.text.SimpleDateFormat;


public class DateCustom {

    public static String getCurrentDate() {
        long itsBennALongDay = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(itsBennALongDay);

    }

    public static String mesAnosDataEscolhida(String data) {
        String[] dataSplit = data.split("/");
        String dataIndex = dataSplit[1] + dataSplit[2];
        return dataIndex;
    }


}
