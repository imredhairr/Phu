package com.phu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * The type Main.
 */
public class Main {

    private ArrayList<Contact> contacts = new ArrayList<Contact>();

    /**
     * Main.
     *
     * @param args the args
     */
    public static void main(String[] args){
        Main cm = new Main();
        cm.selectMenu();
    }

    /**
     * Show menu.
     */
    public void showMenu() {
        System.out.println("1. Load contacts from file");
		System.out.println("2. View all contacts");
		System.out.println("3. Add new contact");
		System.out.println("4. Edit a contact");
		System.out.println("5. Delete a contact");
		System.out.println("6. Search contact list");
		System.out.println("7. Sort contact list");
		System.out.println("8. Save contacts to file");
		System.out.println("9. Quit");
		System.out.println("");
		System.out.print("Select a function (1-9): ");
    }

    /**
     * Select menu.
     */
    public void selectMenu() {
        Scanner input = new Scanner(System.in);
        int choose;
        int contactCount = 0;
        int flag = 0;
        do {
            showMenu();
            choose = input.nextInt();
            if (choose == 1) loadContactsFromFile();
            else if (choose == 2) viewAllContacts();
            else if (choose == 3) addContact();
            else if (choose == 4) editContact();
            else if (choose == 5) deleteContact();
            else if (choose == 6) matchContact();
            else if (choose == 7) sortContact();
            else if (choose == 8) saveContactToFile();
            else if (choose == 9){
                flag = 1;
                quit();
            }
        } while (flag == 0);
    }

    /**
     * Load contacts from file.
     */
    public void loadContactsFromFile() {
        try {
            File file = new File("resource/data/contacts.txt");
            Scanner input = new Scanner(file);
            String name = "";
            String phone = "";
            String email = "";
            String address = "";
            while (input.hasNextLine()) {
                String[] contact = input.nextLine().split("; ");
                if(contact.length > 0) name = contact[0];
                if(contact.length > 1) phone = contact[1];
                if(contact.length > 2) email = contact[2];
                if(contact.length > 3) address = contact[3];
                if (!hasDuplicateContact(name, phone, email, address)) {
                    contacts.add(new Contact(contacts.size() + 1, name, phone, email, address));
                }
            }
            input.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * View all contacts.
     */
    public void viewAllContacts() {
        if (contacts.size() > 0) {
            System.out.println("Contact List: ");
            for (Contact c : contacts) {
                if (c.isActive()) c.view();
            }
        } else {
            System.out.println("No contact in list.");
        }
    }

    /**
     * Add contact.
     */
    public void addContact() {
        Scanner input = new Scanner(System.in);
        String name, phone, email, address;

        System.out.println("Add new contact ");
        System.out.print("Name: ");
        name = input.nextLine();
        System.out.print("Phone: ");
        phone = input.nextLine();
        System.out.print("Email: ");
        email = input.nextLine();
        System.out.print("Address: ");
        address = input.nextLine();

        if (!hasDuplicateContact(name, phone, email, address)) {
            contacts.add(new Contact(contacts.size() + 1, name, phone, email, address));
            System.out.println("Contact add.");
        } else System.out.println("Contact exist.");
    }

    /**
     * Edit contact.
     */
    public void editContact() {
        Scanner input = new Scanner(System.in);
        int contactId;

        System.out.println("Edit contact ");
        System.out.print("Contact ID: ");
        contactId = input.nextInt();
        input.nextLine();

        Contact c = getContactById(contactId);
        if (c != null) {
            String name, phone, email, address;
            System.out.print("Name: ");
            name = input.nextLine();
            System.out.print("Phone: ");
            phone = input.nextLine();
            System.out.print("Email: ");
            email = input.nextLine();
            System.out.print("Address: ");
            address = input.nextLine();
            c.update(contactId, name, phone, email, address);
            System.out.println("Contact update.");
        } else System.out.println("Contact not found.");
    }

    /**
     * Delete contact.
     */
    public void deleteContact() {
        Scanner input = new Scanner(System.in);
        int contactId;

        System.out.print("Delete contact ");
        System.out.print("Contact ID: ");
        contactId = input.nextInt();
        input.nextLine();

        Contact c = getContactById(contactId);
        if (c != null) {
            c.setActive(false);
            System.out.println("Contact delete.");
        } else System.out.println("Contact not found.");
    }

    /**
     * Match contact.
     */
    public void matchContact() {
        Scanner input = new Scanner(System.in);
        String key;
        ArrayList<Contact> matchedList;

        System.out.print("Search contact: ");
        key = input.nextLine();

        matchedList = searchContact(key);
        if (matchedList != null) {
            System.out.println("Result: ");
            for (Contact c : matchedList) {
                c.view();
            }
        }
    }

    /**
     * Sort contact.
     */
    public void sortContact() {
        Collections.sort(contacts, Contact.ascNameComparator);
    }

    /**
     * Save contact to file.
     */
    public void saveContactToFile() {
        try {
            FileWriter file = new FileWriter("resource/data/contacts.txt");
            for (Contact c : contacts) {
                file.write(c.getContactAsString() + System.lineSeparator());
            }
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
      FileWriter myWriter = new FileWriter("filename.txt");
      myWriter.write("Files in Java might be tricky, but it is fun enough!");
      myWriter.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    }

    /**
     * Quit.
     */
    public void quit() {
        System.out.print("Good Luck");
    }

    private Contact getContactById(int id) {
        for (Contact c : contacts) {
            if (c.matchById(id) && c.isActive()) {
                return c;
            }
        }

        return null;
    }

    private ArrayList<Contact> searchContact(String key) {
        ArrayList<Contact> searchContacts = new ArrayList<Contact>();
        for (Contact c : contacts) {
            if (c.matches(key) && c.isActive()) {
                searchContacts.add(c);
            }
        }
        if (searchContacts.size() > 0) return searchContacts;
        else return null;
    }

    private boolean hasDuplicateContact(String name, String phone, String email, String address) {
        for (Contact c : contacts) {
            if (c.hasDuplicate(name, phone, email, address)) return true;
        }
        return false;
    }
}