package src;

import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ContactDatabase {
    private File database;

    // Constructor for the ContactDatabase class
    public ContactDatabase() {
        try {
            database = new File("src\\resources\\data\\contacts.csv");
            if (database.createNewFile()) {
                System.out.println("New file created: " + database.getName());
                FileWriter writer = new FileWriter(database);
                writer.write("databaseid,contactid,forename"
                             + ",surname,phone,address,email");
                writer.close();
            }
        } catch (Exception e) {
            System.out.println("An error occured!");
            e.printStackTrace();
        }
    }

    // Count how many rows there are in the database file
    public int countRows() {
        int rows = 0;

        try (BufferedReader reader = new BufferedReader
             (new FileReader(database))) {
            String row;
            while ((row = reader.readLine()) != null) {
                    rows++;
            }
        } catch (Exception e) {
            System.out.println("An error occured!");
            e.printStackTrace();
        }

        return rows;
    }

    // Add new contact to the database
    public void addContact(Contact newContact) {
        int databaseId = countRows();
        newContact.setDatabaseId(databaseId);

        try (BufferedWriter writer = new BufferedWriter
             (new FileWriter(database, true))) {
            writer.newLine();
            writer.write(Integer.toString(newContact.getDatabaseId()) + ','
                         + newContact.getContactId() + ','
                         + newContact.getForename() + ','
                         + newContact.getSurname() + ','
                         + newContact.getPhone() + ','
                         + newContact.getAddress() + ','
                         + newContact.getEmail());
        } catch (Exception e) {
            System.out.println("An error occured!");
            e.printStackTrace();
        }
    }

    // Delete Contact from the database
    public void deleteContact(Contact oldContact) {
        int toBeDeleted = oldContact.getDatabaseId();
        ArrayList<String> contacts = new ArrayList<String>();

        try (BufferedReader reader = new BufferedReader
             (new FileReader(database))) {
            String row;
            int currentRow = 0;
            while ((row = reader.readLine()) != null) {
                if (currentRow != toBeDeleted) {
                    contacts.add(row);
                }
                currentRow++;
            }
        } catch (Exception e) {
            System.out.println("An error occured!");
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter
             (new FileWriter(database))) {
            int newId = 1;
            for (int i = 0; i < contacts.size(); i++) {
                if (i != 0) {
                    String toBeRemoved = "";
                    for (int j = 0; j < contacts.get(i).length(); j++) {
                        toBeRemoved += contacts.get(i).charAt(j);
                        if (contacts.get(i).charAt(j) == ',') {
                            break;
                        }
                    }
                    contacts.set(i, contacts.get(i).replace(toBeRemoved,
                                 (Integer.toString(newId) + ',')));
                    writer.newLine();
                    newId++;
                }
                writer.write(contacts.get(i));
            }
        } catch (Exception e) {
            System.out.println("An error occured!");
            e.printStackTrace();
        }
    }

    // Update contact in the database
    public void updateContact(Contact contact) {
        deleteContact(contact);
        addContact(contact);
    }

    // Get all data from a contact in the database
    public String readFullContact(int databaseId) {
        try (BufferedReader reader = new BufferedReader
             (new FileReader(database))) {
            String row;
            int currentRow = 0;
            while ((row = reader.readLine()) != null) {
                if (currentRow == databaseId) {
                    return row;
                }
                currentRow++;
            }
        } catch (Exception e) {
            System.out.println("An error occured!");
            e.printStackTrace();
        }
        return null;
    }

    // Get full name of a contact in the database
    public String readContactName(int databaseId) {
        String fullname = "";

        try (BufferedReader reader = new BufferedReader
             (new FileReader(database))) {
            String row;
            int currentRow = 0;
            while ((row = reader.readLine()) != null) {
                if (currentRow == databaseId) {
                    fullname = row;
                    break;
                }
                currentRow++;
            }
        } catch (Exception e) {
            System.out.println("An error occured!");
            e.printStackTrace();
        }

        String[] array = fullname.split(",");
        fullname = array[2] + ' ' + array[3];
        return fullname;
    }
}
