package command;

import model.Contact;
import repository.ContactRepository;

public class UpdateCommand implements Command {
    private final ContactRepository repository;
    private final Contact oldContact;
    private final Contact newContact;

    public UpdateCommand(ContactRepository repository, Contact oldContact, Contact newContact) {
        this.repository = repository;
        this.oldContact = oldContact.clone();
        this.newContact = newContact.clone();
    }

    @Override
    public void execute() {
        repository.update(newContact.clone());
    }

    @Override
    public void undo() {
        repository.update(oldContact.clone());
    }
}
