package ServerPackage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

// import org.springframework.context.ApplicationContext;
// import org.springframework.context.support.ClassPathXmlApplicationContext;

public abstract class Behavior implements Runnable{
    Socket s;
    Object[] data;
    public Behavior(Socket s) {
        this.s=s;
        this.data=new Object[2];
    }
    public void send(Object o){
        ObjectOutputStream oos=null;
        try {
            if(!s.isClosed()){
                oos = new ObjectOutputStream(s.getOutputStream());
                oos.writeObject(o);
            }
            // oos.close();
        } catch (IOException e) {
            try {
                s.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }catch(Exception e){
try {
                    s.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
        }
    }
    public Object read(){
        ObjectInputStream ois=null;
        Object o = null;
        try {
            if(!s.isClosed()){

                ois = new ObjectInputStream(s.getInputStream());
                o=ois.readObject();
            }
            // ois.close();
        } catch (Exception e) {
            try {
                s.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }            e.printStackTrace();
        }
        return o;
    }
}
