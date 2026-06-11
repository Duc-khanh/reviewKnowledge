package service;

import model.Contact;
import model.Gender;
import util.StringNormalizer;
import java.util.List;
import java.util.stream.Collectors;

public class SearchService {
    public List<Contact> searchByPhone(List<Contact> contacts, String phoneQuery) {
        if (phoneQuery == null || phoneQuery.trim().isEmpty()) {
            return contacts;
        }
        String q = phoneQuery.trim();
        return contacts.stream()
                .filter(c -> c.getPhone().contains(q))
                .collect(Collectors.toList());
    }

    public List<Contact> searchByName(List<Contact> contacts, String nameQuery) {
        if (nameQuery == null || nameQuery.trim().isEmpty()) {
            return contacts;
        }
        String normalizedQuery = StringNormalizer.normalize(nameQuery);    // normalize tim kiêm khong dau
        return contacts.stream()
                .filter(c -> StringNormalizer.normalize(c.getFullName()).contains(normalizedQuery))
                .collect(Collectors.toList());
    }

    public List<Contact> searchByGroup(List<Contact> contacts, String groupQuery) {
        if (groupQuery == null || groupQuery.trim().isEmpty()) {
            return contacts;
        }
        String g = groupQuery.trim();
        return contacts.stream()
                .filter(c -> c.getGroup().equalsIgnoreCase(g))
                .collect(Collectors.toList());
    }

    public List<Contact> searchCombined(List<Contact> contacts, String nameQuery, String groupQuery, Gender genderQuery) {
        return contacts.stream()
                .filter(c -> {
                    if (nameQuery == null || nameQuery.trim().isEmpty()) return true;
                    return StringNormalizer.normalize(c.getFullName()).contains(StringNormalizer.normalize(nameQuery));
                })
                .filter(c -> {
                    if (groupQuery == null || groupQuery.trim().isEmpty()) return true;
                    return c.getGroup().equalsIgnoreCase(groupQuery.trim());
                })
                .filter(c -> {
                    if (genderQuery == null) return true;
                    return c.getGender() == genderQuery;
                })
                .collect(Collectors.toList());
    }
}
