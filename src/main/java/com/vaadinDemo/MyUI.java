package com.vaadinDemo;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final Label title = new Label ("REGISTER TO USE STOCKFX APPLICATION");
        final VerticalLayout layout = new VerticalLayout();
        final TextField email= new TextField();
        final TextField name = new TextField();
        final TextField surname = new TextField();
        name.setCaption("Enter your Name");
        surname.setCaption("Enter your Surname");
        email.setCaption("Enter your Email");


        Button button = new Button("Enter");
        button.addClickListener(e -> {
            if(email.equals("") & name.equals("")& surname.equals(""))
            {
                layout.addComponent(new Label("Please Check your Fields "));
            }
            else{
            layout.addComponent(new Label("All correct" + name.getValue()
                    +" " + surname.getValue() +", Redirecting..."));
            String emails = email.getValue();
            String names = name.getValue();
            String surnames = surname.getValue();

                try {
                    Class.forName("org.sqlite.JDBC");
                    Connection con = DatabaseConnection.con();
                    String sql = "INSERT INTO ACCOUNTS (Name,Surname,Email) VALUES ('"+names+"','"+surnames+"','"+emails+"')";
                    PreparedStatement ps = con.prepareStatement(sql);

                        ps.setString(1,names);
                        ps.setString(2,surnames);
                        ps.setString(3,emails);
                    ps.execute();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            }});
        
        layout.addComponents(title,name,surname,email,button);
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
