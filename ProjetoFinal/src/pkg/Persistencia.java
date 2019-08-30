package pkg;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Persistencia {
	private String fileName;
	
	public Persistencia() {
		this.fileName = "dados.dat";
	}
	
	public boolean salvar(Cinema object) {
		
		ObjectOutputStream objectOut;
		try {
			objectOut = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(this.fileName)));
			objectOut.writeObject(object);
			objectOut.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}			
		

	}
	
	public Cinema recuperar() {
		
		ObjectInputStream objectIn;
		Cinema so = new Cinema();
		
		try {
			objectIn = new ObjectInputStream(new BufferedInputStream(new FileInputStream(this.fileName)));
			so = (Cinema)objectIn.readObject();
			objectIn.close();
			return so;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return so;
		

	}
}