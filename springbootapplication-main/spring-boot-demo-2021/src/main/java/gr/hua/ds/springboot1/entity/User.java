package gr.hua.ds.springboot1.entity;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "authority")
    private String authority;

    @Column(name = "afm")
    private long afm;

    @Column(nullable = false, columnDefinition = "BIT", length = 1)
    private int enabled;

    public User() {
    }

    public User(String firstName, String lastName, String email, String username, String password, String authority, long afm, int enabled) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.authority = authority;
        this.afm = afm;
        this.enabled = enabled;
    }

    @OneToMany(cascade= CascadeType.ALL, mappedBy="user", fetch = FetchType.EAGER ,orphanRemoval = true)
    private List<Application> applications=new ArrayList<>();

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAuthority() {return authority;}
    public void setAuthority(String authority) {this.authority = authority;}
    public int getEnabled() {return enabled;}
    public void setEnabled(int enabled) {this.enabled = enabled;}
    public int getId() {return id;}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEnable() {
        return enabled;
    }

    public void setEnable(int enable) {
        this.enabled = enable;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void addInApplications(Application application) {
        applications.add(application);
        application.setUser(this);
    }
    public User rmvFromApplications(Application application) {
        applications.remove(application);
        application.setUser(null);
        return this;
    }

    public long getAfm() {
        return afm;
    }

    public void setAfm(long afm) {
        this.afm = afm;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enable=" + enabled +
                '}';
    }
    // add convenience methods for bi-directional relation

}