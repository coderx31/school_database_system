
import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.bson.Document;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Arrays;


public class GUI extends Application {
    private static double xOffset = 0;
    private static double yOffset = 0;
    final private static MongoClient client = new MongoClient(new MongoClientURI("mongodb+srv://coderx:coderx3134@test-cluster1.y2qvm.mongodb.net/users-test?retryWrites=true&w=majority"));
    final private static MongoDatabase database = client.getDatabase("users-test");
    final private static MongoCollection<Document> user = database.getCollection("user");

    @Override
    public void start(Stage primaryStage) throws Exception {
        /*Loading Fonts*/
        FileInputStream poppinsBold = new FileInputStream("resources/Fonts/Poppins-Bold.ttf");
        Font.loadFont(poppinsBold,18);
        /*Shadow effects*/
        DropShadow upperDrop = new DropShadow();
        upperDrop.setBlurType(BlurType.THREE_PASS_BOX);
        upperDrop.setColor(Color.rgb(255,255,255,0.05));
        upperDrop.setOffsetX(-5);
        upperDrop.setOffsetY(-5);

        DropShadow belowDrop = new DropShadow();
        belowDrop.setBlurType(BlurType.THREE_PASS_BOX);
        belowDrop.setColor(Color.rgb(0,0,0,0.5));
        belowDrop.setOffsetY(5);
        belowDrop.setOffsetX(5);

        InnerShadow upper = new InnerShadow();
        upper.setBlurType(BlurType.THREE_PASS_BOX);
        upper.setColor(Color.rgb(255,255,255,0.05));
        upper.setOffsetX(-2);
        upper.setOffsetY(-2);
        InnerShadow below = new InnerShadow();
        below.setBlurType(BlurType.THREE_PASS_BOX);
        below.setColor(Color.rgb(0,0,0,0.8));
        below.setOffsetY(2);
        below.setOffsetX(2);


        /*Scene for login window*/
        AnchorPane loginBody = new AnchorPane();
        loginBody.setId("login-body");
        AnchorPane loginForm = new AnchorPane();
        FontAwesomeIconView closeIcon = new FontAwesomeIconView(FontAwesomeIcon.CLOSE);
        closeIcon.setId("close-icon");
        Label close = new Label("", closeIcon);
        close.setId("close");
        close.setLayoutX(775);
        close.setLayoutY(5);
        close.setOnMouseClicked(e -> System.exit(0));
        loginForm.setId("login-form");
        loginForm.setPrefSize(400,500);
        loginForm.setLayoutX(200);
        loginForm.setLayoutY(50);
        loginForm.setEffect(upperDrop);
        upperDrop.setInput(belowDrop);
        FontAwesomeIconView userIcon = new FontAwesomeIconView(FontAwesomeIcon.USER);
        userIcon.setId("user-icon");
        Label login = new Label("LOGIN",userIcon);
        login.setContentDisplay(ContentDisplay.LEFT);
        login.setGraphicTextGap(20);
        login.setId("login");
        login.setLayoutX(130);
        login.setLayoutY(50);
        Label username = new Label("Username");
        username.setId("user-name");
        username.setLayoutX(50);
        username.setLayoutY(130);
        TextField txt_username = new TextField();
        txt_username.setId("textField");
        txt_username.setPromptText("example@coderx.com");
        txt_username.setLayoutX(70);
        txt_username.setLayoutY(160);
        Label password = new Label("Password");
        password.setId("password");
        password.setLayoutX(50);
        password.setLayoutY(230);
        PasswordField txt_pass = new PasswordField();
        txt_pass.setId("textField");
        String dot = new StringBuilder().appendCodePoint(0x2022).toString();
        txt_pass.setPromptText(dot+dot+dot+dot+dot+dot);
        txt_pass.setLayoutX(70);
        txt_pass.setLayoutY(260);
        Button signIn = new Button("Sign In");
        signIn.setId("sign-in");
        signIn.setLayoutX(70);
        signIn.setLayoutY(330);
        signIn.setEffect(upperDrop);
        upperDrop.setInput(belowDrop);
        Label lbl_signUp = new Label("Don't have account ?");
        lbl_signUp.setId("signup-label");
        lbl_signUp.setLayoutX(90);
        lbl_signUp.setLayoutY(400);
        Label click = new Label("Click here.");
        click.setId("click");
        click.setLayoutX(260);
        click.setLayoutY(400);
        loginForm.getChildren().addAll(login,username,txt_username,password,txt_pass,signIn,lbl_signUp, click);
        loginBody.getChildren().addAll(loginForm,close);
        Scene loginScene = new Scene(loginBody,800,600);
        loginScene.setFill(Color.TRANSPARENT);
        loginScene.getStylesheets().add("/CSS/loginStyle.css");

        /*Sign Up Window*/

        AnchorPane signUpBody = new AnchorPane();
        signUpBody.setId("signup-body");
        FontAwesomeIconView closeIcon2 = new FontAwesomeIconView(FontAwesomeIcon.CLOSE);
        closeIcon2.setId("close-icon");
        Label close2 = new Label("", closeIcon2);
        close2.setId("close");
        close2.setLayoutX(775);
        close2.setLayoutY(5);
        close2.setOnMouseClicked(e -> System.exit(0));
        FontAwesomeIconView usersIcon = new FontAwesomeIconView(FontAwesomeIcon.USERS);
        usersIcon.setId("user-icon");
        Label signUp = new Label("Sign Up",usersIcon);
        signUp.setContentDisplay(ContentDisplay.LEFT);
        signUp.setGraphicTextGap(20);
        signUp.setId("signUp");
        signUp.setLayoutX(330);
        signUp.setLayoutY(50);
        TextField txt_firstName = new TextField();
        txt_firstName.setId("txt");
        txt_firstName.setPromptText("First Name");
        txt_firstName.setLayoutX(50);
        txt_firstName.setLayoutY(150);
        TextField txt_lastName = new TextField();
        txt_lastName.setId("txt");
        txt_lastName.setPromptText("Last Name");
        txt_lastName.setLayoutX(430);
        txt_lastName.setLayoutY(150);
        TextField txt_email = new TextField();
        txt_email.setId("txt-email");
        txt_email.setPromptText("example@coderx.com");
        txt_email.setLayoutX(50);
        txt_email.setLayoutY(215);
        PasswordField txt_password = new PasswordField();
        txt_password.setId("txt");
        txt_password.setPromptText("Enter Password");
        txt_password.setLayoutX(50);
        txt_password.setLayoutY(280);
        PasswordField txt_password2 = new PasswordField();
        txt_password2.setId("txt");
        txt_password2.setPromptText("Enter Password");
        txt_password2.setLayoutX(430);
        txt_password2.setLayoutY(280);

        Button btnSubmit = new Button("Submit");
        btnSubmit.setId("submit");
        btnSubmit.setLayoutX(200);
        btnSubmit.setLayoutY(380);
        btnSubmit.setEffect(upperDrop);
        upperDrop.setInput(belowDrop);
        btnSubmit.setOnMouseClicked(e -> {
            try {
                addUsers(txt_firstName,txt_lastName,txt_email,txt_password,txt_password2);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });

        Label haveAcc = new Label("Already have Account ?");
        haveAcc.setId("downLink");
        haveAcc.setLayoutX(230);
        haveAcc.setLayoutY(440);
        Label clickHere = new Label("Click here");
        clickHere.setId("click");
        clickHere.setLayoutX(430);
        clickHere.setLayoutY(440);
        signUpBody.getChildren().addAll(close2,signUp,txt_firstName,txt_lastName,txt_email,txt_password,
                txt_password2,btnSubmit,haveAcc,clickHere);
        Scene signUpScene = new Scene(signUpBody,800,600);
        signUpScene.setFill(Color.TRANSPARENT);
        signUpScene.getStylesheets().add("/CSS/signUpStyle.css");
        click.setOnMouseClicked(e -> primaryStage.setScene(signUpScene));
        clickHere.setOnMouseClicked(e -> primaryStage.setScene(loginScene));

        /*Dashboard*/

        AnchorPane dashboardBody = new AnchorPane();
        dashboardBody.setId("dashboard-body");
        FontAwesomeIconView closeIcon3 = new FontAwesomeIconView(FontAwesomeIcon.CLOSE);
        closeIcon3.setId("close-icon");
        Label close3 = new Label("", closeIcon3);
        close3.setId("close");
        close3.setLayoutX(775);
        close3.setLayoutY(5);
        close3.setOnMouseClicked(e -> System.exit(0));
        Label lbl_dashboard = new Label("Dashboard");
        lbl_dashboard.setId("dashboard-label");
        lbl_dashboard.setLayoutX(20);
        lbl_dashboard.setLayoutY(20);
        Button btnAddStudent = new Button("Add Student");
        btnAddStudent.setId("add-student");
        btnAddStudent.setLayoutX(75);
        btnAddStudent.setLayoutY(100);
        btnAddStudent.setEffect(upperDrop);
        upperDrop.setInput(belowDrop);

        Button btnAddTeacher = new Button("Add Teacher");
        btnAddTeacher.setId("add-student");
        btnAddTeacher.setLayoutX(250);
        btnAddTeacher.setLayoutY(100);
        btnAddTeacher.setEffect(upperDrop);
        upperDrop.setInput(belowDrop);

        dashboardBody.getChildren().addAll(close3,lbl_dashboard,btnAddStudent,btnAddTeacher);
        Scene dashboardScene = new Scene(dashboardBody,800,600);
        dashboardScene.setFill(Color.TRANSPARENT);
        dashboardScene.getStylesheets().add("/CSS/dashboardStyle.css");
        signIn.setOnMouseClicked(e -> {
            try {
                loginAuthentication(txt_username, txt_pass,primaryStage,dashboardScene);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });

        /*Add student window*/
        AnchorPane studentBody = new AnchorPane();
        studentBody.setId("student-body");
        FontAwesomeIconView closeIcon4 = new FontAwesomeIconView(FontAwesomeIcon.CLOSE);
        closeIcon4.setId("close-icon");
        Label close4 = new Label("", closeIcon2);
        close4.setId("close");
        close4.setLayoutX(775);
        close4.setLayoutY(5);
        close4.setOnMouseClicked(e -> System.exit(0));
        Label student = new Label("Students");
        student.setId("students");
        student.setLayoutX(350);
        student.setLayoutY(20);
        TextField stId = new TextField();
        stId.setId("st-text");
        stId.setPromptText("Student ID");
        stId.setLayoutY(80);
        stId.setLayoutX(50);
        TextField stFName = new TextField();
        stFName.setId("st-text");
        stFName.setPromptText("First Name");
        stFName.setLayoutX(50);
        stFName.setLayoutY(145);
        TextField stLName = new TextField();
        stLName.setId("st-text");
        stLName.setPromptText("Last Name");
        stLName.setLayoutY(145);
        stLName.setLayoutX(430);
        TextField stEmail = new TextField();
        stEmail.setId("st-text");
        stEmail.setPromptText("Student Email");
        stEmail.setLayoutX(50);
        stEmail.setLayoutY(210);
        TextField stMobile = new TextField();
        stMobile.setId("st-text");
        stMobile.setPromptText("Student Mobile");
        stMobile.setLayoutX(430);
        stMobile.setLayoutY(210);
        TextField stAge = new TextField();
        stAge.setId("st-text");
        stAge.setPromptText("Student Age");
        stAge.setLayoutY(275);
        stAge.setLayoutX(50);
        TextField stSex = new TextField();
        stSex.setId("st-text");
        stSex.setPromptText("Student Gender");
        stSex.setLayoutX(430);
        stSex.setLayoutY(275);
        TextField stYear = new TextField();
        stYear.setId("st-text");
        stYear.setPromptText("Year");
        stYear.setLayoutX(50);
        stYear.setLayoutY(340);
        TextField stCourse = new TextField();
        stCourse.setId("st-text");
        stCourse.setPromptText("Course");
        stCourse.setLayoutX(430);
        stCourse.setLayoutY(340);
        Button stSubmit = new Button("Submit");
        stSubmit.setId("st-submit");
        stSubmit.setLayoutY(475);
        stSubmit.setLayoutX(100);
        stSubmit.setEffect(upperDrop);
        upperDrop.setInput(belowDrop);
        Button stCancel = new Button("Cancel");
        stCancel.setId("st-cancel");
        stCancel.setLayoutX(430);
        stCancel.setLayoutY(475);
        stCancel.setEffect(upperDrop);
        upperDrop.setInput(belowDrop);
        studentBody.getChildren().addAll(close4,student,stId,stFName,stLName,stEmail,stMobile, stAge,
                stSex,stSubmit,stCancel, stYear, stCourse);
        Scene studentScene = new Scene(studentBody,800,600);
        studentScene.setFill(Color.TRANSPARENT);
        studentScene.getStylesheets().add("/CSS/studentStyle.css");
        btnAddStudent.setOnMouseClicked(e -> primaryStage.setScene(studentScene));
        stSubmit.setOnMouseClicked( e -> {
            try {
                addStudents(stFName,stLName,stId,stEmail,stMobile,stAge,stSex,stYear,stCourse);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        stCancel.setOnMouseClicked( e -> primaryStage.setScene(dashboardScene));

        /*Add Teacher window*/
        AnchorPane teacherBody = new AnchorPane();
        teacherBody.setId("teacher-body");
        FontAwesomeIconView closeIcon5 = new FontAwesomeIconView(FontAwesomeIcon.CLOSE);
        closeIcon5.setId("close-icon");
        Label close5 = new Label("", closeIcon2);
        close5.setId("close");
        close5.setLayoutX(775);
        close5.setLayoutY(5);
        close5.setOnMouseClicked(e -> System.exit(0));
        Label teacher = new Label("Teacher");
        teacher.setId("teacher");
        teacher.setLayoutX(350);
        teacher.setLayoutY(20);
        TextField tId = new TextField();
        tId.setId("t-text");
        tId.setPromptText("Teacher ID");
        tId.setLayoutY(80);
        tId.setLayoutX(50);
        TextField tFName = new TextField();
        tFName.setId("t-text");
        tFName.setPromptText("First Name");
        tFName.setLayoutX(50);
        tFName.setLayoutY(145);
        TextField tLName = new TextField();
        tLName.setId("t-text");
        tLName.setPromptText("Last Name");
        tLName.setLayoutY(145);
        tLName.setLayoutX(430);
        TextField tEmail = new TextField();
        tEmail.setId("t-text");
        tEmail.setPromptText("Teacher Email");
        tEmail.setLayoutX(50);
        tEmail.setLayoutY(210);
        TextField tMobile = new TextField();
        tMobile.setId("t-text");
        tMobile.setPromptText("Teacher Mobile");
        tMobile.setLayoutX(430);
        tMobile.setLayoutY(210);
        TextField tAge = new TextField();
        tAge.setId("t-text");
        tAge.setPromptText("Teacher Age");
        tAge.setLayoutY(275);
        tAge.setLayoutX(50);
        TextField tSex = new TextField();
        tSex.setId("t-text");
        tSex.setPromptText("Teacher Gender");
        tSex.setLayoutX(430);
        tSex.setLayoutY(275);
        TextField tField = new TextField();
        tField.setId("t-text");
        tField.setPromptText("Field");
        tField.setLayoutX(50);
        tField.setLayoutY(340);
        TextField tFaculty = new TextField();
        tFaculty.setId("t-text");
        tFaculty.setPromptText("Faculty");
        tFaculty.setLayoutX(430);
        tFaculty.setLayoutY(340);
        Button tSubmit = new Button("Submit");
        tSubmit.setId("t-submit");
        tSubmit.setLayoutY(475);
        tSubmit.setLayoutX(100);
        tSubmit.setEffect(upperDrop);
        upperDrop.setInput(belowDrop);
        Button tCancel = new Button("Cancel");
        tCancel.setId("t-cancel");
        tCancel.setLayoutX(430);
        tCancel.setLayoutY(475);
        tCancel.setEffect(upperDrop);
        upperDrop.setInput(belowDrop);
        teacherBody.getChildren().addAll(close5,teacher,tId,tFName,tLName,tEmail,tMobile, tAge,
                tSex,tSubmit,tCancel, tField, tFaculty);
        Scene teacherScene = new Scene(teacherBody, 800,600);
        teacherScene.setFill(Color.TRANSPARENT);
        btnAddTeacher.setOnMouseClicked(e -> primaryStage.setScene(teacherScene));
        teacherScene.getStylesheets().add("/CSS/teacherStyle.css");
        tSubmit.setOnMouseClicked( e -> {
            try {
                addTeacher(tFName,tLName,tId,tEmail,tMobile,tAge,tSex,tField,tFaculty);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        tCancel.setOnMouseClicked( e -> primaryStage.setScene(dashboardScene));

        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(loginScene);
        primaryStage.show();

        /*Effect Method Test*/
        buttonEffect(signIn,btnSubmit,btnAddStudent,btnAddTeacher,stSubmit,stCancel,tSubmit,tCancel);
        textFieldEffect(txt_username,txt_firstName,txt_lastName,txt_email,stId,stFName,stLName,stEmail,stMobile,
                stAge,stSex,stYear,stCourse,tId,tFName,tLName,tEmail,tMobile,tAge,tSex,tField,tFaculty,txt_pass,txt_password,
                txt_password2);
        /*Moving Method*/
        stageMoveEffect(loginBody,signUpBody,dashboardBody,studentBody,teacherBody,primaryStage);
    }

    public static void main(String[] args) {
        launch();
    }

    private static void loginAuthentication(TextField email, PasswordField password, Stage stage, Scene scene) throws FileNotFoundException {
        /* using MongoDB we need to create this authentication System */
        boolean check = false;
        FindIterable<Document> results = user.find(new BasicDBObject( "email",email.getText()));
        for (Document result : results){
           String pass = (String)result.get("password");
           if (pass.equals(password.getText())){
               stage.setScene(scene);
               check = true;
           }
        }
        if (!check){
            System.out.println("No such user!");
            UserError.UserErrorDisplay("Invalid User ! Try Again");
        }





    }

    private static void addUsers(TextField firstName, TextField lastName, TextField email, PasswordField password,
                                 PasswordField password2) throws FileNotFoundException {
        /*adding the users to database*/
        if(firstName.getText().matches("^[a-zA-Z ]+$") && lastName.getText().matches("^[a-zA-Z ]+$") &&
        email.getText().matches("^([a-z\\d\\.-]+)@([a-z\\d-]+)\\.([a-z]{2,8})(\\.[a-z]{2,8})?$")){
            if (password.getText().equals(password2.getText())) {
                Document document = new Document("fistName", firstName.getText())
                        .append("lastName", lastName.getText())
                        .append("email", email.getText())
                        .append("password", password.getText())
                        .append("password2", password2.getText());

                user.insertOne(document);
                System.out.println("Your Account Created !");
            }else{
                UserError.UserErrorDisplay("Password not Match !");
            }


        }else{
            UserError.UserErrorDisplay("Invalid Input ! Please Check Again");
        }



    }

    private static void addStudents(TextField FName, TextField LName, TextField id, TextField email, TextField mobile,
                                    TextField age, TextField sex, TextField year, TextField course) throws FileNotFoundException {
        /*Setting Data into Student Object*/
        Student student = new Student();
        if (FName.getText().matches("^[a-zA-Z ]+$") && LName.getText().matches("^[a-zA-Z ]+$") &&
        id.getText().matches("^[0-9]{7}$") && email.getText().matches("^([a-z\\d\\.-]+)@([a-z\\d-]+)\\.([a-z]{2,8})(\\.[a-z]{2,8})?$")
        && mobile.getText().matches("^(077|076|071|072|075)\\d{7}$") && age.getText().matches("^[0-9]{1,2}$") &&
        sex.getText().matches("^(m|f|M|F){1}$") && year.getText().matches("^[0-9]{4}$") && course.getText().matches("^[a-zA-Z ]+$")) {

            student.setFirstName(FName.getText());
            student.setLastName(LName.getText());
            student.setId(id.getText());
            student.setEmail(email.getText());
            student.setMobile(mobile.getText());
            student.setAge(Integer.parseInt(age.getText()));
            student.setSex(sex.getText());
            student.setYear(year.getText());
            student.setCourse(course.getText());
            /*Connection to SQL DB*/
            String uri = "jdbc:mysql://localhost/schooldb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String user_name = "root";
            String password = "";
            String query = "INSERT INTO student VALUES (?,?,?,?,?,?,?,?,?)";

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection dbConnection = DriverManager.getConnection(uri, user_name, password);
                System.out.println("Connected to MySql Server");
                PreparedStatement insert = dbConnection.prepareStatement(query);
                insert.setString(1, student.getId());
                insert.setString(2,student.getFirstName());
                insert.setString(3,student.getLastName());
                insert.setString(4,student.getEmail());
                insert.setString(5,student.getMobile());
                insert.setInt(6, student.getAge());
                insert.setString(7,student.getSex());
                insert.setString(8,student.getYear());
                insert.setString(9,student.getCourse());
                insert.executeUpdate();
                System.out.println("Successfully added to dataBase");

            } catch (Exception e) {
                e.printStackTrace();
                UserError.UserErrorDisplay("Database Error !");
            }
        }else{
            UserError.UserErrorDisplay("Invalid Inputs !");
        }

        /*After adding clear the all input field*/
        ArrayList<TextField> inputFields = new ArrayList<>(Arrays.asList(id,FName,LName,email,mobile,age,
                sex,year,course));
        for (int i=0; i<inputFields.size(); i++){
            inputFields.get(i).setText("");
        }



    }

    private static void addTeacher(TextField FName, TextField LName, TextField id, TextField email, TextField mobile,
                                   TextField age, TextField sex, TextField field, TextField faculty) throws FileNotFoundException {
        /*Setting data into Teacher Object*/
        Teacher teacher = new Teacher();
        if (FName.getText().matches("^[a-zA-Z ]+$") && LName.getText().matches("^[a-zA-Z ]+$") &&
                id.getText().matches("^[0-9]{7}$") && email.getText().matches("^([a-z\\d\\.-]+)@([a-z\\d-]+)\\.([a-z]{2,8})(\\.[a-z]{2,8})?$")
                && mobile.getText().matches("^(077|076|071|072|075)\\d{7}$") && age.getText().matches("^[0-9]{1,2}$") &&
                sex.getText().matches("^(m|f|M|F){1}$") && field.getText().matches("^[a-zA-Z ]+$") && faculty.getText().matches("^[a-zA-Z ]+$")){

            teacher.setFirstName(FName.getText());
            teacher.setLastName(LName.getText());
            teacher.setId(id.getText());
            teacher.setEmail(email.getText());
            teacher.setMobile(mobile.getText());
            teacher.setAge(Integer.parseInt(age.getText()));
            teacher.setSex(sex.getText());
            teacher.setField(field.getText());
            teacher.setFaculty(faculty.getText());

            /*Connecting SQL DataBase*/

            String uri = "jdbc:mysql://localhost/schooldb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String user_name = "root";
            String password = "";
            String query = "INSERT INTO teacher VALUES (?,?,?,?,?,?,?,?,?)";

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection dbConnection = DriverManager.getConnection(uri, user_name, password);
                System.out.println("Connected to MySql Server");
                PreparedStatement insert = dbConnection.prepareStatement(query);
                insert.setString(1, teacher.getId());
                insert.setString(2,teacher.getFirstName());
                insert.setString(3,teacher.getLastName());
                insert.setString(4,teacher.getEmail());
                insert.setString(5,teacher.getMobile());
                insert.setInt(6, teacher.getAge());
                insert.setString(7,teacher.getSex());
                insert.setString(8,teacher.getField());
                insert.setString(9,teacher.getFaculty());
                insert.executeUpdate();
                System.out.println("Successfully added to dataBase");

            } catch (Exception e) {
                UserError.UserErrorDisplay("Database error !");
            }


        }else{
            UserError.UserErrorDisplay("Invalid Inputs !");
        }

        /*After adding clear the all input field*/
        ArrayList<TextField> inputFields = new ArrayList<>(Arrays.asList(id,FName,LName,email,mobile,age,
                sex,field,faculty));
        for (int i=0; i<inputFields.size(); i++){
            inputFields.get(i).setText("");
        }

    }

    private static void buttonEffect(Button button1, Button button2, Button button3, Button button4, Button button5,
                                     Button button6, Button button7, Button button8){
        /*Shadow effects*/
        DropShadow upperDrop = new DropShadow();
        upperDrop.setBlurType(BlurType.THREE_PASS_BOX);
        upperDrop.setColor(Color.rgb(255,255,255,0.05));
        upperDrop.setOffsetX(-5);
        upperDrop.setOffsetY(-5);

        DropShadow belowDrop = new DropShadow();
        belowDrop.setBlurType(BlurType.THREE_PASS_BOX);
        belowDrop.setColor(Color.rgb(0,0,0,0.5));
        belowDrop.setOffsetY(5);
        belowDrop.setOffsetX(5);

        InnerShadow upper = new InnerShadow();
        upper.setBlurType(BlurType.THREE_PASS_BOX);
        upper.setColor(Color.rgb(255,255,255,0.05));
        upper.setOffsetX(-2);
        upper.setOffsetY(-2);
        InnerShadow below = new InnerShadow();
        below.setBlurType(BlurType.THREE_PASS_BOX);
        below.setColor(Color.rgb(0,0,0,0.8));
        below.setOffsetY(2);
        below.setOffsetX(2);

        /*Neumorphic View Effect */
        ArrayList<Button> buttons = new ArrayList<>(Arrays.asList(button1,button2,button3,button4,button5,
                button6, button7,button8));

        for (int i=0; i<buttons.size(); i++){
            int finalI = i;
            buttons.get(i).setOnMousePressed(e ->{
                buttons.get(finalI).setEffect(upper);
                upper.setInput(below);
            });

        }
        for (int i=0; i<buttons.size(); i++){
            int finalI = i;
            buttons.get(i).setOnMouseReleased(e ->{
                buttons.get(finalI).setEffect(upperDrop);
                upperDrop.setInput(belowDrop);
            });
        }
    }

    private static void textFieldEffect(TextField textField1, TextField textField2, TextField textField3, TextField textField4,
                                        TextField textField5, TextField textField6, TextField textField7, TextField textField8,
                                        TextField textField9, TextField textField10, TextField textField11, TextField textField12,
                                        TextField textField13, TextField textField14, TextField textField15, TextField textField16,
                                        TextField textField17, TextField textField18, TextField textField19, TextField textField20,
                                        TextField textField21, TextField textField22,PasswordField passwordField1, PasswordField passwordField2,
                                        PasswordField passwordField3){
        InnerShadow upper = new InnerShadow();
        upper.setBlurType(BlurType.THREE_PASS_BOX);
        upper.setColor(Color.rgb(255,255,255,0.05));
        upper.setOffsetX(-2);
        upper.setOffsetY(-2);
        InnerShadow below = new InnerShadow();
        below.setBlurType(BlurType.THREE_PASS_BOX);
        below.setColor(Color.rgb(0,0,0,0.8));
        below.setOffsetY(2);
        below.setOffsetX(2);

        /*TextField Effect*/
        ArrayList<TextField> textFields = new ArrayList<>(Arrays.asList(textField1,textField2,textField3,textField4,textField5
        ,textField6,textField7,textField8,textField9,textField10,textField11,textField12,textField13,textField14,textField15,
                textField16,textField17,textField18,textField19,textField20,textField21,textField22));

        ArrayList<PasswordField> passwordFields = new ArrayList<>(Arrays.asList(passwordField1,passwordField2,passwordField3));

        for (int i=0; i< textFields.size(); i++){
            textFields.get(i).setEffect(upper);
            upper.setInput(below);
        }

        for (int i=0; i< passwordFields.size(); i++){
            passwordFields.get(i).setEffect(upper);
            upper.setInput(below);
        }
    }

    private static void stageMoveEffect(AnchorPane anchorPane1, AnchorPane anchorPane2, AnchorPane anchorPane3, AnchorPane anchorPane4,
                                        AnchorPane anchorPane5,Stage stage){
        ArrayList<AnchorPane> anchorPanes = new ArrayList<>(Arrays.asList(anchorPane1, anchorPane2, anchorPane3,
                anchorPane4,anchorPane5));


        for (int i=0; i<anchorPanes.size(); i++){
            anchorPanes.get(i).setOnMousePressed(e ->{
                xOffset = e.getSceneX();
                yOffset = e.getSceneY();
            });
            anchorPanes.get(i).setOnMouseDragged( e ->{
                stage.setX(e.getScreenX() - xOffset);
                stage.setY(e.getScreenY() - yOffset);
            });
        }
    }
}
