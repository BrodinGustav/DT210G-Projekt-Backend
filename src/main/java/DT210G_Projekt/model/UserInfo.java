package DT210G_Projekt.model;

public class UserInfo {
    private Integer id;
    private String email;
    
    public UserInfo(Integer id, String email) {
        this.id = id;
        this.email = email;
      }
    public Integer getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    
    
}