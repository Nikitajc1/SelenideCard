import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
    }

    public String threeDaysAfter() {
        String day = String.valueOf(LocalDate.now().getDayOfMonth() + 3);

        String month = String.valueOf(LocalDate.now().getMonthValue());

        if(!(month.equals("10") || month.equals("11") || month.equals("12"))) {
            month = "0" + month;
        }

        String year = String.valueOf(LocalDate.now().getYear());

        return day + "." + month + "." + year;
    }
}
