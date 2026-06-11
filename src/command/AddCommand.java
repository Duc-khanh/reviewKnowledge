package command;

import model.Contact;
import repository.ContactRepository;

public class AddCommand implements Command {
    private final ContactRepository repository;
    private final Contact contact;

    public AddCommand(ContactRepository repository, Contact contact) {
        this.repository = repository;
        this.contact = contact.clone();
    }

    @Override
    public void execute() {
        repository.add(contact.clone());
    }

    @Override
    public void undo() {
        repository.delete(contact.getId());
    }
}
