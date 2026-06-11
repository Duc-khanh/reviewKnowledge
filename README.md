# Contact Manager Pro v2.0

Ứng dụng quản lý danh bạ nâng cao (Contact Manager Pro) chạy trên dòng lệnh (Console UI). Dự án được áp dụng toàn diện kiến thức Java Core nâng cao: OOP phân tầng, Collections Framework, Generic Repository, Command Pattern cho tính năng Undo/Redo, Exception Handling tùy biến, Java Time API, Stream API và xử lý tìm kiếm tiếng Việt không dấu.

## Cấu trúc thư mục dự án

```
src/
├── exception/
│   ├── AppException.java              (Ngoại lệ checked gốc)
│   ├── DuplicatePhoneException.java   (Ném khi trùng số điện thoại)
│   ├── ContactNotFoundException.java  (Ném khi không tìm thấy danh bạ)
│   └── InvalidDataException.java      (Ném khi dữ liệu đầu vào lỗi định dạng)
├── model/
│   ├── Contact.java                   (Đối tượng danh bạ POJO đầy đủ, implements Comparable & Cloneable)
│   └── Gender.java                    (Enum giới tính: MALE, FEMALE, OTHER)
├── repository/
│   ├── Repository.java                (Generic Interface Repository)
│   └── ContactRepository.java         (Triển khai Repository lưu trữ danh bạ trong bộ nhớ)
├── service/
│   ├── ContactService.java            (Nghiệp vụ chính: Thêm, Sửa, Xóa, đọc/ghi CSV, giữ hai Stack Deque để Undo/Redo)
│   ├── StatisticsService.java         (Thống kê dữ liệu và sinh nhật bằng Stream API)
│   └── SearchService.java             (Tìm kiếm nâng cao kết hợp lọc đa luồng)
├── command/
│   ├── Command.java                   (Interface Command: execute / undo)
│   ├── AddCommand.java                (Thực thi và hoàn tác thêm mới)
│   ├── UpdateCommand.java             (Thực thi và hoàn tác cập nhật)
│   └── DeleteCommand.java             (Thực thi và hoàn tác xóa bỏ)
├── util/
│   ├── CsvUtil.java                   (Đọc và ghi CSV, tự động backup file cũ trước khi ghi đè)
│   ├── Validator.java                 (Kiểm tra tính hợp lệ của số điện thoại, email, ngày sinh)
│   ├── StringNormalizer.java          (Chuẩn hóa chuỗi tiếng Việt không dấu để phục vụ tìm kiếm)
│   └── AppLogger.java                 (Hệ thống tự ghi log ra file log/app.log theo thời gian thực)
└── ui/
    └── ConsoleUI.java                 (Giao diện dòng lệnh tương tác trực quan, phân trang hiển thị 5 mục/trang)
```

## Hướng dẫn biên dịch và chạy chương trình

### Yêu cầu hệ thống
- **Java Development Kit (JDK)**: Phiên bản 11 trở lên.
- **Môi trường**: Hệ điều hành Windows, macOS, hoặc Linux có cài đặt Java.

### Cách biên dịch dự án
Tại thư mục gốc của dự án (`onTap/`), chạy lệnh sau để biên dịch toàn bộ code Java vào thư mục `out/`:

**Trên Windows (PowerShell):**
```powershell
javac -d out -encoding UTF-8 (Get-ChildItem -Path src -Filter *.java -Recurse).FullName
```

**Trên Linux / macOS / Git Bash:**
```bash
javac -d out -encoding UTF-8 src/Main.java src/exception/*.java src/model/*.java src/repository/*.java src/service/*.java src/command/*.java src/util/*.java src/ui/*.java
```

### Cách chạy chương trình
Sau khi biên dịch thành công, khởi chạy chương trình bằng lệnh:
```bash
java -cp out Main
```

## Các tính năng nổi bật
1. **Xem danh sách & Sắp xếp**: Phân trang 5 mục/trang, hỗ trợ sắp xếp đa tiêu chí (theo họ tên A-Z/Z-A, theo ngày sinh cũ đến mới, hoặc nhóm rồi đến tên dùng Comparator composite) và lọc kết hợp (Nhóm & Giới tính).
2. **Thêm mới**: Số điện thoại bắt buộc (kiểm tra trùng lặp), họ tên và nhóm bắt buộc, kiểm tra định dạng email và ngày sinh (không cho phép ngày trong tương lai). Tự động sinh ID bằng UUID.
3. **Cập nhật**: Tìm kiếm theo số điện thoại cần sửa, cho phép nhấn Enter để giữ nguyên giá trị cũ của từng trường.
4. **Xóa**: Hiển thị tóm tắt thông tin và yêu cầu xác nhận Y/N trước khi xóa.
5. **Tìm kiếm nâng cao**: Tìm gần đúng theo số điện thoại, tìm họ tên tiếng Việt không cần dấu (ví dụ: gõ `nguyen van a` sẽ tìm thấy `Nguyễn Văn A` nhờ `StringNormalizer`), tìm chính xác theo nhóm, và lọc đa tiêu chí (tên + nhóm + giới tính) bằng Stream API.
6. **Thống kê & Nhắc sinh nhật**: Báo cáo tổng số, tỷ lệ nam/nữ, tuổi trung bình, nhóm phổ biến nhất và phân bố nhóm bằng các biểu thức Stream API. Nhắc nhở sinh nhật sắp tới trong vòng 7 ngày tới, tính toán số ngày còn lại (xử lý chính xác trường hợp năm nhuận và sinh nhật vắt qua năm mới).
7. **Đọc/Ghi file CSV**: Đọc và ghi file `data/contacts.csv`. Tự động tạo thư mục nếu chưa tồn tại. Tự động backup file CSV cũ thành `contacts_backup_yyyyMMdd_HHmmss.csv` trước khi ghi đè để bảo vệ dữ liệu. Bỏ qua dòng CSV bị lỗi cấu trúc hoặc ngày sinh sai định dạng và ghi chi tiết vào log file.
8. **Undo & Redo (Command Pattern)**: Hỗ trợ Undo và Redo tối đa 10 bước cho các thao tác Thêm, Sửa, Xóa. Lịch sử được lưu trữ độc lập trong Deque Stack, thông báo thân thiện khi hết lịch sử.
9. **Ghi log chuyên nghiệp**: Tự viết logger (`AppLogger`) ghi log có phân cấp `[INFO]`, `[WARN]`, `[ERROR]` cùng nhãn thời gian thực tế vào `log/app.log`. Không in stack trace ra console người dùng, chỉ thông báo lỗi tiếng Việt dễ hiểu.
