import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main {

    public static void main(String[] args) {
        System.out.println(montarCalendarioAno(2022));
    }

    private static String montarCalendarioAno(int ano){
        String anoMontado = "";
        for (int mes = Calendar.JANUARY; mes <= Calendar.DECEMBER; mes++) {
            anoMontado= anoMontado.concat(montarCalendarioMes(mes, ano)).concat("\n");
        }
        return anoMontado;
    }

    private static String montarCalendarioMes(int mes, int ano) {
        Calendar c = inicializarCalendario(mes, ano);

        String mesMontado=montarCabecalho(mes,ano);
        String semana = "";

        for (int dia = 1; dia <= c.getActualMaximum(Calendar.DAY_OF_MONTH); dia++) {
            c.set(Calendar.DAY_OF_MONTH, dia);

            if (dia == 1) semana = verificarEmQueDiaDaSemanaIniciaOMes(c, semana);

            if (c.get(Calendar.DAY_OF_WEEK) < Calendar.SATURDAY) {
                semana = semana.concat(String.format("%02d", c.get(Calendar.DAY_OF_MONTH)) + " - ");
            } else {
                semana = semana.concat(String.format("%02d", c.get(Calendar.DAY_OF_MONTH)));
                mesMontado = mesMontado.concat(semana).concat("\n");
                semana = "";
            }
            mesMontado = mesMontado.concat(tratarExibicaoDaUltimaSemanaDoMes(c, semana));
        }
        return mesMontado;
    }

    private static Calendar inicializarCalendario(int mes, int ano) {
        Calendar c = new GregorianCalendar();
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.MONTH, mes);
        c.set(Calendar.YEAR, ano);
        return c;
    }

    private static String montarCabecalho(int mes, int ano) {
        String cabecalho = "--------------\n";
        cabecalho = cabecalho.concat(String.format("%02d",mes + 1).concat("/").concat(ano+"\n"));
        cabecalho = cabecalho.concat("--------------\n");
        cabecalho = cabecalho.concat("su - mo - tu - we - th - fr - sat\n");
        return cabecalho;
    }

    private static String tratarExibicaoDaUltimaSemanaDoMes(Calendar c, String semana) {
        String ultimaSemana = "";
        if (c.get(Calendar.DAY_OF_MONTH) == c.getActualMaximum(Calendar.DAY_OF_MONTH)
                && c.get(Calendar.DAY_OF_WEEK) < Calendar.SATURDAY) {
            ultimaSemana =  (semana.substring(0, semana.lastIndexOf("-") - 1));
        }
        return ultimaSemana;
    }

    private static String verificarEmQueDiaDaSemanaIniciaOMes(Calendar c, String semana) {
        int diaEmVerificacao = c.get(Calendar.DAY_OF_WEEK);

        for (int diaCorrente=Calendar.SUNDAY; diaCorrente<=Calendar.SATURDAY; diaCorrente++){
            if(diaEmVerificacao!=diaCorrente) semana=semana.concat("XX - ");else break;
        }
        return semana;
    }
}
