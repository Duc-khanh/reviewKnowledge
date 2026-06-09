public class NextDayCalculator {

    public static String getNextDay(int day, int month, int year) {
        int maxDay = getMaxDayOfMonth(month, year);

        if (day < maxDay) {
            day++;
        } else {
            day = 1;

            if (month == 12) {
                month = 1;
                year++;
            } else {
                month++;
            }
        }

        return day + "/" + month + "/" + year;
    }

    public static int getMaxDayOfMonth(int month, int year) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;

            case 4:
            case 6:
            case 9:
            case 11:
                return 30;

            case 2:
                if (isLeapYear(year)) {
                    return 29;
                }
                return 28;

            default:
                return -1;
        }
    }

    public static boolean isLeapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }
}