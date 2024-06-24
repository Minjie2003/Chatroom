package com.example.demo.enity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: qumingnan
 * Date: 2023-11-13
 * Time: 22:43
 */
@Data
public class UserInfo {
    private Integer id;
    private String location;
    private String sex;
    private String username;
    private String accountNum;
    private String password;
    private String mail;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer state;
    private String photo;
    public static final int ONLINE = 1;
    public static final int OFFLINE = 2;
    public static final int BAN = 3;
    public static void main(String[] args) throws ParseException {

    }
}
