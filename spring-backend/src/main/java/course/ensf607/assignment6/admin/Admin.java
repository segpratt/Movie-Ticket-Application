package course.ensf607.assignment6.admin;

import javax.persistence.*;

@Entity
@Table(name = "admin")
public class Admin {

    @Id
    @SequenceGenerator(name = "Admin_sequence", sequenceName = "Admin_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Admin_sequence")
    private Long id;
    private String name;
    private String email;
    private String password;

    public Admin(Long id, String name, String email, String password){
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Admin(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Admin(){};

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
