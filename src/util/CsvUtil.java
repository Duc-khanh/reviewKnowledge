package util;

import model.Contact;
import model.Gender;
import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CsvUtil {
    private static final String CSV_HEADER = "id,phone,fullName,group,gender,address,birthday,email,notes";
    private static final DateTimeFormatter BACKUP_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    public static List<Contact> readCsv(String filePath) throws IOException {
        List<Contact> list = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return list;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNumber = 0;
            boolean isHeader = true;
            while ((line = br.readLine()) != null) {
                lineNumber++;
                if (isHeader) {
                    isHeader = false;
                    continue; // Bỏ qua dòng tiêu đề
                }
                if (line.trim().isEmpty()) {
                    continue;
                }
                
                try {
                    Contact contact = parseLine(line);
                    list.add(contact);
                } catch (Exception e) {
                    AppLogger.warn("Bỏ qua dòng CSV lỗi (dòng " + lineNumber + "): " + e.getMessage());
                }
            }
        }
        return list;
    }

    private static Contact parseLine(String line) throws Exception {
        String[] parts = line.split(",", -1);
        if (parts.length < 9) {
            throw new IllegalArgumentException("thiếu cột dữ liệu (yêu cầu 9 cột, nhận " + parts.length + ")");
        }
        
        String id = parts[0].trim();
        String phone = parts[1].trim();
        String fullName = parts[2].trim();
        String group = parts[3].trim();
        String genderStr = parts[4].trim();
        String address = parts[5].trim();
        String birthdayStr = parts[6].trim();
        String email = parts[7].trim();
        String notes = parts[8].trim();

        if (id.isEmpty()) throw new IllegalArgumentException("thiếu cột id");
        if (phone.isEmpty()) throw new IllegalArgumentException("thiếu cột số điện thoại");
        if (fullName.isEmpty()) throw new IllegalArgumentException("thiếu cột họ tên");
        if (group.isEmpty()) throw new IllegalArgumentException("thiếu cột nhóm");
        
        Gender gender;
        try {
            gender = Gender.valueOf(genderStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("giới tính không hợp lệ: " + genderStr);
        }

        LocalDate birthday = null;
        if (!birthdayStr.isEmpty()) {
            try {
                birthday = LocalDate.parse(birthdayStr, Validator.DATE_FORMATTER);
            } catch (Exception e) {
                throw new IllegalArgumentException("ngày sinh sai định dạng: " + birthdayStr);
            }
        }

        if (!email.isEmpty() && (!email.contains("@") || email.indexOf('@') == 0 || email.indexOf('@') == email.length() - 1)) {
            throw new IllegalArgumentException("thiếu cột email hoặc định dạng sai: " + email);
        }

        return new Contact(id, phone, fullName, group, gender, address, birthday, email, notes);
    }

    public static void writeCsv(String filePath, List<Contact> contacts) throws IOException {
        File file = new File(filePath);
        
        // Tạo thư mục nếu chưa tồn tại
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            Files.createDirectories(parentDir.toPath());
        }

        // Tự động backup nếu file đã tồn tại
        if (file.exists()) {
            String backupFileName = String.format("contacts_backup_%s.csv", LocalDateTime.now().format(BACKUP_FORMATTER));
            File backupFile = new File(parentDir, backupFileName);
            Files.copy(file.toPath(), backupFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            AppLogger.info("Tự động backup file cũ thành " + backupFile.getName());
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {
            pw.println(CSV_HEADER);
            for (Contact c : contacts) {
                pw.println(formatContactToCsv(c));
            }
        }
    }

    private static String formatContactToCsv(Contact c) {
        String birthdayStr = c.getBirthday() != null ? c.getBirthday().format(Validator.DATE_FORMATTER) : "";
        return String.join(",",
                escapeCsv(c.getId()),
                escapeCsv(c.getPhone()),
                escapeCsv(c.getFullName()),
                escapeCsv(c.getGroup()),
                escapeCsv(c.getGender().name()),
                escapeCsv(c.getAddress()),
                escapeCsv(birthdayStr),
                escapeCsv(c.getEmail()),
                escapeCsv(c.getNotes())
        );
    }

    private static String escapeCsv(String val) {
        if (val == null) return "";
        // Thay thế dấu phẩy bằng khoảng trắng để tránh lỗi phân tích cú pháp CSV đơn giản
        return val.replace(",", " ");
    }
}
