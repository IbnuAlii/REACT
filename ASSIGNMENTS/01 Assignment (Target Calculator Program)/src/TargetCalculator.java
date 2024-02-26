import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Scanner;

public class TargetCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the start date (YYYY-MM-DD): ");
        String startDateStr = scanner.nextLine();
        System.out.print("Enter the end date (YYYY-MM-DD): ");
        String endDateStr = scanner.nextLine();
        System.out.print("Enter the total annual target: $");
        double totalAnnualTarget = scanner.nextDouble();

        LocalDate startDate = LocalDate.parse(startDateStr);
        LocalDate endDate = LocalDate.parse(endDateStr);
        YearMonth yearMonth = YearMonth.from(startDate);

        // Calculate the total days in the month and the total working days in the range
        int totalDaysInMonth = yearMonth.lengthOfMonth();
        int workingDaysInRange = 0;
        LocalDate date = startDate;
        while (!date.isAfter(endDate)) {
            if (date.getDayOfWeek().getValue() != 5) { // 5 is Friday
                workingDaysInRange++;
            }
            date = date.plusDays(1);
        }

        int totalFridaysInMonth = (int) startDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.FRIDAY))
                .datesUntil(startDate.with(TemporalAdjusters.lastDayOfMonth()).plusDays(1), Period.ofWeeks(1))
                .count();

        int daysExcludingFridays = totalDaysInMonth - totalFridaysInMonth;

        // Calculate the daily, monthly, and total targets
        double monthlyTargets = totalAnnualTarget/12;
        double dailyTarget = monthlyTargets/daysExcludingFridays; // 2024 is a leap year, so we might use 366
        double totalTarget = dailyTarget * workingDaysInRange;

        System.out.println("Days Excluding Fridays: " + "[" + daysExcludingFridays + "]");
        System.out.println("Days Worked Excluding Fridays: " + "[" + workingDaysInRange + "]");
        System.out.println("Monthly Targets: " + "$" + "[" + monthlyTargets + "]");
        System.out.println("Total Target: " + "$" + totalTarget);

        scanner.close();
    }
}
