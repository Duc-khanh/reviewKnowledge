package command;

import model.Contact;
import repository.ContactRepository;

public class DeleteCommand implements Command {
    private final ContactRepository repository;
    private final Contact contact;

    public DeleteCommand(ContactRepository repository, Contact contact) {
        this.repository = repository;
        this.contact = contact.clone();
    }

    @Override
    public void execute() {
        repository.delete(contact.getId());
    }

    @Override
    public void undo() {
        repository.add(contact.clone());
    }
}
