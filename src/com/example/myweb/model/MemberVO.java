package com.example.myweb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberVO {
    private String userid;
    private String name;
    private String password;
    private String email;
    private String address;
}
