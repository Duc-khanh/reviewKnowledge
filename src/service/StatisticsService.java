package service;

import model.Contact;
import model.Gender;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class StatisticsService {

    public String generateReport(List<Contact> contacts) {
        if (contacts.isEmpty()) {
            return "Danh bạ trống, không có dữ liệu thống kê.";
        }

        int total = contacts.size();

        Map<Gender, Long> genderCount = contacts.stream()
                .collect(Collectors.groupingBy(Contact::getGender, Collectors.counting())); // nhóm giới tính
        long male = genderCount.getOrDefault(Gender.MALE, 0L);                // số l giới tính
        long female = genderCount.getOrDefault(Gender.FEMALE, 0L);
        long other = genderCount.getOrDefault(Gender.OTHER, 0L);

        double avgAge = contacts.stream()  // tính tuổi tbinh
                .filter(c -> c.getBirthday() != null)
                .collect(Collectors.averagingInt(c -> Period.between(c.getBirthday(), LocalDate.now()).getYears()));

        Map<String, Long> groupDistribution = contacts.stream()    // nhom
                .collect(Collectors.groupingBy(Contact::getGroup, Collectors.counting()));

        String maxGroupName = "Không có";
        long maxGroupCount = 0;
        Map.Entry<String, Long> maxGroupEntry = groupDistribution.entrySet().stream()
                .max(Map.Entry.comparingByValue())  // tim max
                .orElse(null);
        if (maxGroupEntry != null) {
            maxGroupName = maxGroupEntry.getKey();
            maxGroupCount = maxGroupEntry.getValue();
        }

        LocalDate today = LocalDate.now();
        List<String> birthdayReminders = new ArrayList<>();
        
        contacts.stream()
                .filter(c -> c.getBirthday() != null)
                .map(c -> {
                    LocalDate bday = c.getBirthday();   // lấy ngày sn góc
                    LocalDate nextBirthday;
                    try {
                        nextBirthday = bday.withYear(today.getYear());    // tạo sn năm nay
                    } catch (Exception e) {
                        nextBirthday = LocalDate.of(today.getYear(), 3, 1);
                    }
                    if (nextBirthday.isBefore(today)) {
                        try {
                            nextBirthday = nextBirthday.withYear(today.getYear() + 1);  // sn đã qua + thêm 1 năm
                        } catch (Exception e) {
                            nextBirthday = LocalDate.of(today.getYear() + 1, 3, 1);
                        }
                    }
                    long daysRemaining = ChronoUnit.DAYS.between(today, nextBirthday);    // tinh ngày con lại   ,  ChronoUnit.DAYS tính theo dvi ngay
                    return new BirthdayInfo(c, daysRemaining, nextBirthday);                  // .between Kcách từ startDate đến endDate
                })
                .filter(info -> info.daysRemaining >= 0 && info.daysRemaining <= 7)  // lọc người sn trong 7 ngày tới
                .sorted(Comparator.comparingLong(info -> info.daysRemaining))
                .forEach(info -> {
                    String dateStr = info.nextBirthday.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM"));
                    String daysStr = info.daysRemaining == 0 ? "hôm nay" : info.daysRemaining + " ngày nữa";
                    birthdayReminders.add(String.format("  - %-15s (%s) — %s (%s)", 
                            info.contact.getFullName(), info.contact.getPhone(), daysStr, dateStr));
                });

        StringBuilder sb = new StringBuilder();
        sb.append("═══════════════════════════════════════════\n");
        sb.append("          BÁO CÁO DANH BẠ\n");
        sb.append("═══════════════════════════════════════════\n");
        sb.append(String.format("Tổng số danh bạ     : %d\n", total));
        sb.append(String.format("Theo giới tính      : Nam: %d | Nữ: %d | Khác: %d\n", male, female, other));
        sb.append(String.format("Tuổi trung bình     : %.1f\n", avgAge));
        sb.append(String.format("Nhóm nhiều nhất     : %s (%d người)\n", maxGroupName, maxGroupCount));
        sb.append("Phân bố theo nhóm   :\n");
        
        groupDistribution.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .forEach(e -> sb.append(String.format("  - %-16s: %d\n", e.getKey(), e.getValue())));
        
        sb.append("─────────────────────────────────────────\n");
        sb.append("Sinh nhật trong 7 ngày tới:\n");
        if (birthdayReminders.isEmpty()) {
            sb.append("  Không có ai có sinh nhật trong 7 ngày tới.\n");
        } else {
            for (String reminder : birthdayReminders) {
                sb.append(reminder).append("\n");
            }
        }
        sb.append("═══════════════════════════════════════════");
        return sb.toString();
    }

    private static class BirthdayInfo {
        final Contact contact;
        final long daysRemaining;
        final LocalDate nextBirthday;

        BirthdayInfo(Contact contact, long daysRemaining, LocalDate nextBirthday) {
            this.contact = contact;
            this.daysRemaining = daysRemaining;
            this.nextBirthday = nextBirthday;
        }
    }
}
