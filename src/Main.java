import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main {

    public static void main(String[] args) {
        imprimirCalendarioAno(2022);
    }

    private static void imprimirCalendarioAno(int ano){
        for (int mes = Calendar.JANUARY; mes <= Calendar.DECEMBER; mes++) {
            imprimirCalendarioMes(mes, ano);
        }
    }
    private static void imprimirCalendarioMes(int mes, int ano) {
        Calendar c = inicializarCalendario(mes, ano);

        imprimirCabecalho(mes,ano);

        String semana = "";
        for (int dia = 1; dia <= c.getActualMaximum(Calendar.DAY_OF_MONTH); dia++) {
            c.set(Calendar.DAY_OF_MONTH, dia);

            if (dia == 1) semana = verificarEmQueDiaDaSemanaIniciaOMes(c, semana);

            if (c.get(Calendar.DAY_OF_WEEK) < Calendar.SATURDAY) {
                semana = semana.concat(String.format("%02d", c.get(Calendar.DAY_OF_MONTH)) + " - ");
            } else {
                semana = semana.concat(String.format("%02d", c.get(Calendar.DAY_OF_MONTH)));
                System.out.println(semana);
                semana = "";
            }
            tratarExibicaoDaUltimaSemanaDoMes(c, semana);
        }
    }

    private static Calendar inicializarCalendario(int mes, int ano) {
        Calendar c = new GregorianCalendar();
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.MONTH, mes);
        c.set(Calendar.YEAR, ano);
        return c;
    }

    private static void imprimirCabecalho(int mes, int ano) {
        System.out.println("--------------");
        System.out.println(String.format("%02d",mes + 1).concat("/").concat(ano+""));
        System.out.println("--------------");
        System.out.println("su - mo - tu - we - th - fr - sat");
    }

    private static void tratarExibicaoDaUltimaSemanaDoMes(Calendar c, String semana) {
        if (c.get(Calendar.DAY_OF_MONTH) == c.getActualMaximum(Calendar.DAY_OF_MONTH)
                && c.get(Calendar.DAY_OF_WEEK) < Calendar.SATURDAY) {
            System.out.println(semana.substring(0, semana.lastIndexOf("-") - 1));
        }
    }

    private static String verificarEmQueDiaDaSemanaIniciaOMes(Calendar c, String semana) {
        boolean conhecoODiaDaSemana = false;
        int diaDaSemanaEmVerificacao = c.get(Calendar.DAY_OF_WEEK);

        for (int diaDaSemanaCorrente=Calendar.SUNDAY; diaDaSemanaCorrente<=Calendar.SATURDAY; diaDaSemanaCorrente++){
            if(diaDaSemanaEmVerificacao!=diaDaSemanaCorrente && !conhecoODiaDaSemana)
                semana=semana.concat("XX - ");
            else conhecoODiaDaSemana = true;
        }
        return semana;
    }
}
