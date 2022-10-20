package com.example.demo.algorithm.tool;

import java.io.*;

public class SaveAndLoad {
    private static int count = 0;
    public static void writeObjectToFile(Object obj, String path)
    {
        System.out.println("modelpath"+path);
        File file =new File("model/" + path);
        count++;
        FileOutputStream out;
        try {
            out = new FileOutputStream(file);
            ObjectOutputStream objOut=new ObjectOutputStream(out);
            objOut.writeObject(obj);
            objOut.flush();
            objOut.close();
            System.out.println("write object success!");
        } catch (IOException e) {
            System.out.println("write object failed");
            e.printStackTrace();
        }
    }

    public static Object readObjectFromFile(String path)
    {
        Object temp=null;
        File file =new File( System.getProperty("user.dir") + "/model/" + path);
        FileInputStream in;
        try {
            in = new FileInputStream(file);
            ObjectInputStream objIn=new ObjectInputStream(in);
            temp=objIn.readObject();
            objIn.close();
            System.out.println("read object success!");
        } catch (IOException e) {
            System.out.println("read object failed");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return temp;
    }
}
