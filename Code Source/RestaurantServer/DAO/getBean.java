package DAO;

import java.sql.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class getBean {
        ApplicationContext context = new ClassPathXmlApplicationContext("./config.xml");
        Connect connection = (Connect)context.getBean("connect");
        protected Connection conn = connection.getConnection();
}
