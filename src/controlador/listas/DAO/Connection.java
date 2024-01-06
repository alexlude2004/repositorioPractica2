/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.listas.DAO;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import java.io.File;
import java.security.Permission;

/**
 *
 * @author Asus
 */
public class Connection {
    //Data es la ruta de la carpeta dentro del proyecto; prohibido pegar la ruta en sí.
    private static String URL = "data/"+File.separatorChar;
    private static XStream xstream;
    private Connection(){
        
    }

    public static String getURL() {
        return URL;
    }

    public static XStream getXstream() {
        if(xstream == null){
            xstream = new XStream(new JettisonMappedXmlDriver());
            //xstream.setMode(xstream.NO_REFERENCES);Causaba el problema de solo mostrar head y last
            xstream.addPermission(AnyTypePermission.ANY);
        }
        return xstream;
    }
    
    
}
