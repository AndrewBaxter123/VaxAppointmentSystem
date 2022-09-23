package sample;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main extends Application {

    public static VaxCentre headVC; //list
    public static Patient headP; // list

    public static Patient findP(String PPSN){
    Patient current = headP;

    while(current!=null){
        if(current.getPpsNumber().equals(PPSN)){
            return current;
        } current = current.getNextPatient();
    } return null;
}

    @SuppressWarnings("unchecked")
    public static void load() throws Exception //VC
    {
        XStream xstream = new XStream(new DomDriver());
        xstream.addPermission(AnyTypePermission.ANY);
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("VaxCentres.xml")); // one for Vax Centre
        headVC = (VaxCentre) is.readObject();
        is.close();
    }

    public static void save() throws Exception //VC
    {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("VaxCentres.xml"));
        out.writeObject(headVC);
        out.close();
    }

    @SuppressWarnings("unchecked")
    public static void loadPatient() throws Exception
    {
        XStream xstream = new XStream(new DomDriver());
        xstream.addPermission(AnyTypePermission.ANY);
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("Patients.xml")); // one for Vax Centre
        headP = (Patient) is.readObject();
        is.close();
    }

    public static void savePatient() throws Exception
    {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("Patients.xml"));
        out.writeObject(headP);
        out.close();
    }



    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("FXML/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 600, 475));
        try {
            load();
        } catch(Exception e){
            System.out.println("Problem!" + e);
        }
        try{
            loadPatient();
        } catch(Exception e){
            System.out.println("Patient Problem" + e);
        }

        primaryStage.show();
    }


    public static void main(String[] args){
        launch(args);

    }
}
