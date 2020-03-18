package by.epam.jwd.entity;
import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = -4899639796230566611L;

    private int id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String email;
    private int discount;
    private Role role;

    public User() {
    }

    public User(int id, String name, String surname, int discount, String email, String login, String password, Role role) {
        this.id = id;
    	this.name = name;
        this.surname = surname;
        this.discount = discount;
        this.email = email;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((surname == null) ? 0 : surname.hashCode());
        result = prime * result + ((login == null) ? 0 : login.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((role == null) ? 0 : 1);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        User other = (User) obj;
        return (id == other.getId()) && ((name != null && name.equals(other.getName())))
                && ((surname != null && surname.equals(other.getSurname())))
                && ((login != null && login.equals(other.getLogin())))
                && ((password != null && password.equals(other.getPassword())))
                && ((email != null && email.equals(other.getEmail())))
                && (discount == other.getDiscount())
                && (role == other.getRole() || (role != null && role.equals(other.getRole())));
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [id=" + id + ", name=" + name + ", surname=" + surname + ", login=" + login + ", password="
                + password + ", email=" + email + ", discount=" + discount + ", role=" + role + "]";
    }


}