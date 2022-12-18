import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main {

    public static void main(String[] args) {
        for (int i = Calendar.JANUARY; i <= Calendar.DECEMBER; i++) {
            imprimeCalendario(i);
        }
    }

    private static void imprimeCalendario(int mes) {
        Calendar c = new GregorianCalendar();
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.MONTH, mes);
        c.set(Calendar.YEAR, 2022);
        String semana = "";

        System.out.println("--------------");
        System.out.println("mes: " + (mes + 1));
        System.out.println("--------------");
        System.out.println("su - mo - tu - we - th - fr - sat");

        for (int dia = 1; dia <= c.getActualMaximum(Calendar.DAY_OF_MONTH); dia++) {
            c.set(Calendar.DAY_OF_MONTH, dia);

            if (dia == 1) semana = verificaEmQueDiaDaSemanaIniciaOMes(c, semana);

            if (c.get(Calendar.DAY_OF_WEEK) < Calendar.SATURDAY) {
                semana = semana.concat(String.format("%02d", c.get(Calendar.DAY_OF_MONTH)) + " - ");
            } else {
                semana = semana.concat(String.format("%02d", c.get(Calendar.DAY_OF_MONTH)));
                System.out.println(semana);
                semana = "";
            }
            trataExibicaoDaUltimaSemanaDoMes(c, semana);
        }
    }

    private static void trataExibicaoDaUltimaSemanaDoMes(Calendar c, String semana) {
        if (c.get(Calendar.DAY_OF_MONTH) == c.getActualMaximum(Calendar.DAY_OF_MONTH)
                && c.get(Calendar.DAY_OF_WEEK) < Calendar.SATURDAY) {
            System.out.println(semana.substring(0, semana.lastIndexOf("-") - 1));
        }
    }

    private static String verificaEmQueDiaDaSemanaIniciaOMes(Calendar c, String semana) {
        boolean conhecoODiaDaSemana = false;
        int diaDaSemanaDoDia1 = c.get(Calendar.DAY_OF_WEEK);

        for (int diaDaSemana=Calendar.SUNDAY; diaDaSemana<=Calendar.SATURDAY; diaDaSemana++){
            if(diaDaSemanaDoDia1!=diaDaSemana && !conhecoODiaDaSemana)semana=semana.concat("XX - ");else conhecoODiaDaSemana = true;
        }
        return semana;
    }
}
