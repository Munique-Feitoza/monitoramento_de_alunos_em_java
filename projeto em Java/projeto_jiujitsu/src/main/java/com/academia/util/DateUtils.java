package com.academia.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    private static final String FORMATO_DATA = "dd/MM/yyyy";

    // Método para formatar uma data para o formato dd/MM/yyyy
    public static String formatarData(Date data) {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_DATA);
        return sdf.format(data);
    }

    // Método para converter uma string para o tipo Date
    public static Date converterStringParaData(String dataString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_DATA);
        return sdf.parse(dataString);
    }

    // Método para verificar se uma data está no formato correto
    public static boolean isDataValida(String dataString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_DATA);
            sdf.setLenient(false);
            sdf.parse(dataString);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
