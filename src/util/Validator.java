package util;

import exception.InvalidDataException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class Validator {
    private static final Pattern PHONE_PATTERN = Pattern.compile("^0\\d{9,10}$");
    // Email regex: needs '@' and a domain with extension like .com, .vn, etc.
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void validatePhone(String phone) throws InvalidDataException {
        if (phone == null || phone.trim().isEmpty()) {
            throw new InvalidDataException("Số điện thoại là bắt buộc.");
        }
        if (!PHONE_PATTERN.matcher(phone.trim()).matches()) {
            throw new InvalidDataException("Số điện thoại sai định dạng - chỉ chứa chữ số, độ dài 10–11 ký tự, bắt đầu bằng 0.");
        }
    }

    public static void validateEmail(String email) throws InvalidDataException {
        if (email == null || email.trim().isEmpty()) {
            return; // email is optional
        }
        if (!EMAIL_PATTERN.matcher(email.trim()).matches() || !email.contains("@")) {
            throw new InvalidDataException("Email nhập vào không chứa @ hoặc không có domain hợp lệ.");
        }
    }

    public static LocalDate validateBirthday(String birthdayStr) throws InvalidDataException {
        if (birthdayStr == null || birthdayStr.trim().isEmpty()) {
            return null; // birthday is optional
        }
        try {
            LocalDate date = LocalDate.parse(birthdayStr.trim(), DATE_FORMATTER);
            if (date.isAfter(LocalDate.now())) {
                throw new InvalidDataException("Ngày sinh không được là ngày trong tương lai.");
            }
            return date;
        } catch (DateTimeParseException e) {
            throw new InvalidDataException("Ngày sinh sai định dạng dd/MM/yyyy hoặc không hợp lệ.");
        }
    }

    public static void validateRequired(String value, String fieldName) throws InvalidDataException {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidDataException(fieldName + " là bắt buộc và không được để trống.");
        }
    }
}
