package DT210G_Projekt.service;

import DT210G_Projekt.model.User;

public interface UserService {
    
     void register(String email, String password);
    User authenticate(String email, String password);
    
}
