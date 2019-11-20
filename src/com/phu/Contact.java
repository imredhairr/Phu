package com.phu;

import java.util.Comparator;

/**
 * The type Contact.
 */
public class Contact{
    /**
     * The constant ascNameComparator.
     */
    public static Comparator<Contact> ascNameComparator = new Comparator<Contact>() {
        public int compare(Contact c1, Contact c2) {
            return c1.getName().toUpperCase().compareTo(c2.getName().toUpperCase());
        }
    };
    /**
     * The constant descNameComparator.
     */
    public static Comparator<Contact> descNameComparator = new Comparator<Contact>() {
        public int compare(Contact c1, Contact c2) {
            return c2.getName().toUpperCase().compareTo(c1.getName().toUpperCase());
        }
    };
    private int id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private boolean active;

    /**
     * Instantiates a new Contact.
     *
     * @param id      the id
     * @param name    the name
     * @param phone   the phone
     * @param email   the email
     * @param address the address
     */
    public Contact(int id, String name, String phone, String email, String address) {
        setId(id);
        setName(name);
        setPhone(phone);
        setEmail(email);
        setAddress(address);
        setActive(true);
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        if (validateString(name)) this.name = name;
    }

    /**
     * Gets phone.
     *
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets phone.
     *
     * @param phone the phone
     */
    public void setPhone(String phone) {
        if (validateString(phone)) this.phone = phone;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        if (validateString(email)) this.email = email;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(String address) {
        if (validateString(address)) this.address = address;
    }

    /**
     * Is active boolean.
     *
     * @return the boolean
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets active.
     *
     * @param active the active
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;

        Contact contact = (Contact) o;

        if (getId() != contact.getId()) return false;
        if (isActive() != contact.isActive()) return false;
        if (getName() != null ? !getName().equals(contact.getName()) : contact.getName() != null) return false;
        if (getPhone() != null ? !getPhone().equals(contact.getPhone()) : contact.getPhone() != null) return false;
        if (getEmail() != null ? !getEmail().equals(contact.getEmail()) : contact.getEmail() != null) return false;
        return getAddress() != null ? getAddress().equals(contact.getAddress()) : contact.getAddress() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        result = 31 * result + (isActive() ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id='" + getId() +
                ", name='" + getName() + '\'' +
                ", phone='" + getPhone() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", active=" + isActive() +
                '}';
    }

    /**
     * Has duplicate boolean.
     *
     * @param name    the name
     * @param phone   the phone
     * @param email   the email
     * @param address the address
     * @return the boolean
     */
    public boolean hasDuplicate(String name, String phone, String email, String address) {
        return getName().equals(name) && getPhone().equals(phone) && getEmail().equals(email) && getAddress().equals(address);
    }

    /**
     * Gets contact as string.
     *
     * @return the contact as string
     */
    public String getContactAsString() {
        return getName() + "; " + getPhone() + "; " + getEmail() + "; " + getAddress();
    }

    /**
     * Update int.
     *
     * @param id      the id
     * @param name    the name
     * @param phone   the phone
     * @param email   the email
     * @param address the address
     * @return the int
     */
    public int update(int id, String name, String phone, String email, String address) {
        if (getId() == id && isActive()) {
            setName(name);
            setPhone(phone);
            setEmail(email);
            setAddress(address);
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * Matches boolean.
     *
     * @param key the key
     * @return the boolean
     */
    public boolean matches(String key) {
        boolean result = false;
        if (validateString(getName())) result = result || getName().matches("(.*)" + key + "(.*)");
        if (validateString(getPhone())) result = result || getPhone().matches("(.*)" + key + "(.*)");
        if (validateString(getEmail())) result = result || getEmail().matches("(.*)" + key + "(.*)");
        if (validateString(getAddress())) result = result || getAddress().matches("(.*)" + key + "(.*)");

        return result;
    }

    /**
     * Match by id boolean.
     *
     * @param id the id
     * @return the boolean
     */
    public boolean matchById(int id) {
        return getId() == id;
    }

    /**
     * View.
     */
    public void view() {
        System.out.println();
        System.out.println("ID: " + getId());
        System.out.println("Name: " + getName());
        System.out.println("Phone: " + getPhone());
        System.out.println("Email: " + getEmail());
        System.out.println("Address: " + getAddress());
        System.out.println("==================================");
    }

    private boolean validateString(String str) {
        return ((str != null) && !str.isEmpty() && !str.isBlank());
    }
}
