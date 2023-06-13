package com.LabelVie.GestionStockLbv.Service;

import com.LabelVie.GestionStockLbv.Entity.User;

public interface UserService {
    User creatUser(User user);

    boolean checkEmail(String email);

    User findByEmail(String email);
}
