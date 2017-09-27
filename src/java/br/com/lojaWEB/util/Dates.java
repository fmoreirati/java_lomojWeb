package util;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Dates {

    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");

    public static boolean checkDate(String data) {
        try {
            String[] dt = data.split("/");
            int ano = Integer.parseInt(dt[2]);
            int mes = Integer.parseInt(dt[1]);
            int dia = Integer.parseInt(dt[0]);
            if ((dia < 1) || (dia > 31)) {
                return true;
            }
            if (((dia == 30) || (dia == 31)) && (mes == 2)) {
                return true;
            }
            if ((dia == 31) && ((mes == 2) || (mes == 4) || (mes == 6) || (mes == 9) || (mes == 11))) {
                return true;
            }
            if ((mes < 1) || (mes > 12)) {
                return true;
            }
            if ((dia == 29) && (mes == 2) && (new GregorianCalendar().isLeapYear(ano) == false)) {
                return true;
            }
        } catch (Exception e) {
            return true;
        }
        return false;
    }

    public static Date strToDate(String umaData) {
        String[] dt = umaData.split("/");
        int ano = Integer.parseInt(dt[2]);
        int mes = Integer.parseInt(dt[1]);
        int dia = Integer.parseInt(dt[0]);

        return Date.valueOf(ano + "-" + mes + "-" + dia);
    }

    public static Calendar strtoDate(String umaData) {
        Calendar dtc = Calendar.getInstance();
        dtc.setTime(strToDate(umaData));
        return dtc;
    }

    public static String dateToStr(Date umaData) {
        if (umaData == null) {
            return "";
        } else {
            return SDF.format(umaData);
        }
    }

    public static String dateToStr(Calendar umaData) {
        if (umaData == null) {
            return "";
        } else {
            DateFormat formataData = DateFormat.getDateInstance();
            return formataData.format(umaData.getTime());
        }
    }

    public static String dateSystem() {
        try {
            DateFormat dateFormat = SDF;
            GregorianCalendar date = new GregorianCalendar();
            return dateFormat.format(date.getTime());
        } catch (DateTimeException e) {
            System.err.println("Erros ao pegar a data do sistema: " + e.toString());
            return null;
        }
    }

    public static String dateToMysql(Calendar data) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return dateFormat.format(data.getTime());
        } catch (DateTimeException e) {
            System.err.println("Erros ao converter data para texto: " + e.toString());
            return null;
        }

    }

    public static Calendar mysqlToDate(Date data) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Calendar date = Calendar.getInstance();
            date.setTime(Date.valueOf(dateFormat.format(data)));
            return date;
        } catch (DateTimeException e) {
            System.err.println("Erros ao converter texto em data: " + e.toString());
            return null;
        }

    }
}
