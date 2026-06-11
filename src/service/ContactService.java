package service;

import command.*;
import exception.*;
import model.Contact;
import repository.ContactRepository;
import util.AppLogger;
import util.CsvUtil;
import util.Validator;

import java.io.IOException;
import java.util.*;

public class ContactService {
    private final ContactRepository repository;
    private final Deque<Command> undoStack = new ArrayDeque<>();
    private final Deque<Command> redoStack = new ArrayDeque<>();

    public ContactService(ContactRepository repository) {
        this.repository = repository;
    }

    public ContactRepository getRepository() {
        return repository;
    }

    private void pushToUndo(Command cmd) {
        if (undoStack.size() >= 10) {   //lưu 10 thao tac
            undoStack.removeLast();
        }
        undoStack.push(cmd);
    }

    private void pushToRedo(Command cmd) {
        if (redoStack.size() >= 10) {
            redoStack.removeLast();
        }
        redoStack.push(cmd);
    }

    public void addContact(Contact contact) throws DuplicatePhoneException, InvalidDataException {
        Validator.validatePhone(contact.getPhone());
        Validator.validateRequired(contact.getFullName(), "Họ tên");
        Validator.validateRequired(contact.getGroup(), "Nhóm");
        Validator.validateEmail(contact.getEmail());

        if (repository.findByPhone(contact.getPhone()) != null) {  // kiem tra sdt co ton tai
            throw new DuplicatePhoneException("Số điện thoại " + contact.getPhone() + " đã tồn tại trong hệ thống.");
        }

        contact.setId(UUID.randomUUID().toString());

        Command cmd = new AddCommand(repository, contact);
        cmd.execute();

        pushToUndo(cmd);
        redoStack.clear();
        
        AppLogger.info("Đã thêm danh bạ mới: " + contact.getFullName() + " (" + contact.getPhone() + ")");
    }

    public void updateContact(String oldPhone, Contact updatedData) throws ContactNotFoundException, DuplicatePhoneException, InvalidDataException {
        Contact existing = repository.findByPhone(oldPhone);
        if (existing == null) {
            throw new ContactNotFoundException("Không tìm thấy danh bạ với số điện thoại: " + oldPhone);
        }

        Validator.validatePhone(updatedData.getPhone());
        Validator.validateRequired(updatedData.getFullName(), "Họ tên");
        Validator.validateRequired(updatedData.getGroup(), "Nhóm");
        Validator.validateEmail(updatedData.getEmail());

        if (!existing.getPhone().equals(updatedData.getPhone())) {
            if (repository.findByPhone(updatedData.getPhone()) != null) {
                throw new DuplicatePhoneException("Số điện thoại " + updatedData.getPhone() + " đã tồn tại trong hệ thống.");
            }
        }

        updatedData.setId(existing.getId());

        Command cmd = new UpdateCommand(repository, existing, updatedData);
        cmd.execute();

        pushToUndo(cmd);
        redoStack.clear();

        AppLogger.info("Đã cập nhật danh bạ: " + existing.getFullName() + " -> " + updatedData.getFullName());
    }

    public void deleteContact(String phone) throws ContactNotFoundException {
        Contact existing = repository.findByPhone(phone);
        if (existing == null) {
            throw new ContactNotFoundException("Không tìm thấy danh bạ với số điện thoại: " + phone);
        }

        Command cmd = new DeleteCommand(repository, existing);
        cmd.execute();

        pushToUndo(cmd);
        redoStack.clear();

        AppLogger.info("Đã xóa danh bạ: " + existing.getFullName() + " (" + existing.getPhone() + ")");
    }

    public void undo() throws AppException {
        if (undoStack.isEmpty()) {
            throw new AppException("Không còn thao tác nào để hoàn tác (Undo).");
        }
        Command cmd = undoStack.pop();
        cmd.undo();
        pushToRedo(cmd);
        AppLogger.info("Đã hoàn tác (Undo) thao tác thành công.");
    }

    public void redo() throws AppException {
        if (redoStack.isEmpty()) {
            throw new AppException("Không còn thao tác nào để làm lại (Redo).");
        }
        Command cmd = redoStack.pop();
        cmd.execute();
        pushToUndo(cmd);
        AppLogger.info("Đã làm lại (Redo) thao tác thành công.");
    }

    public List<Contact> getAllContacts() {
        return repository.findAll();
    }

    public void loadFromCsv(String filePath) throws IOException {
        repository.clear();
        List<Contact> loaded = CsvUtil.readCsv(filePath);
        for (Contact c : loaded) {
            repository.add(c);
        }
        AppLogger.info("Đã đọc " + loaded.size() + " danh bạ từ " + filePath);
    }

    public void saveToCsv(String filePath) throws IOException {
        List<Contact> contacts = repository.findAll();
        CsvUtil.writeCsv(filePath, contacts);
        AppLogger.info("Đã ghi " + contacts.size() + " danh bạ vào " + filePath);
    }

    public Contact findByPhone(String phone) {
        return repository.findByPhone(phone);
    }
}
