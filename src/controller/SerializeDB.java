package controller;

import java.io.*;

/**
 * This class contains methods read from a binary file containing persisted objects
 * from SerializedDB
 */
public class SerializeDB {
    /**
     * This is a method used to read a serialized object from .dat file. It will read the data based on the given
     * file name.
     *
     * @param filename The file name of the .dat database to be loaded into the application
     * @return The data that was written into the .dat database
     */
    public static SerializedDB readSerializedObject(String filename) {

        SerializedDB serializedDB = null;
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream(filename);
            in = new ObjectInputStream(fis);
            serializedDB = (SerializedDB) in.readObject();
            in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        // print out the size
        //System.out.println(" Details Size: " + pDetails.size());
        //System.out.println();
        return serializedDB;
    }

    /**
     * This is a method used to write a serialized object into a .dat file. It will write the data into the file
     * based on the given file name.
     *
     * @param filename The file name of the .dat database in order to save the data from the application into
     *                 the .dat database file.
     */
    public static void writeSerializedObject(String filename, SerializedDB serializedDB) {

        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(filename);
            out = new ObjectOutputStream(fos);
            out.writeObject(serializedDB);
            out.close();
            //	System.out.println("Object Persisted");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
