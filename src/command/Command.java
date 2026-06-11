package command;

public interface Command {
    void execute();   // Thực thi lệnh
    void undo();      // Hoàn tác lệnh
}
