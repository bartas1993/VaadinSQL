package com.vaadinDemo;

import javax.servlet.annotation.WebServlet;
import javax.xml.bind.Binder;

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
        layout.setSpacing(true);
        layout.setMargin(true);
        final TextField email= new TextField();
        final TextField name = new TextField();
        final TextField surname = new TextField();
        final PasswordField pass = new PasswordField();
        final PasswordField passRepeat = new PasswordField();
        name.setCaption("Enter your Name");
        surname.setCaption("Enter your Surname");
        email.setCaption("Enter your Email");
        pass.setCaption("Enter Password");
        passRepeat.setCaption("Repeat your Password");

        Button button2 = new Button("Test");
        button2.addClickListener(e->{




        });
        Button button = new Button("Enter");
        button.addClickListener(e -> {
            if(email.equals("") & name.equals("")& surname.equals(""))
            {
                layout.addComponent(new Label("Please Check your Fields "));
            }
            else{
            layout.addComponent(new Label("Information Correct " + name.getValue()));

                String emails = email.getValue();
                String names = name.getValue();
                String surnames = surname.getValue();
                String password = pass.getValue();

                try {
                    Class.forName("org.sqlite.JDBC");
                    Connection con = DatabaseConnection.con();
                    String sql = "INSERT INTO ACCOUNTS (Name,Surname,Email,Password) VALUES ('" + names + "','" + surnames + "','" + emails + "','"+password+"')";
                    PreparedStatement ps = con.prepareStatement(sql);
                    boolean insert = ps.execute();
                    while (insert == true) {
                        ps.setString(1, names);
                        ps.setString(2, surnames);
                        ps.setString(3, emails);
                        ps.setString(4, password);

                    }
                    layout.addComponent(new Label("Directing to the next Page"));
                    next();

                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            }

            });
        
        layout.addComponents(title,name,surname,email,pass,passRepeat,button);
        setContent(layout);
    }
    public void next()
    {
    final VerticalLayout vert = new VerticalLayout();
        Label tit = new Label("Personal Details");
        TextField user = new TextField("Username");
        CheckBox male = new CheckBox("Male");
        CheckBox female = new CheckBox("Female");
        final HorizontalLayout hor = new HorizontalLayout();
        vert.addComponents(tit,user,male,female);

    setContent(vert);

    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
