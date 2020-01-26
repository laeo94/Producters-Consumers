import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Producteur extends Thread {

	public T tampon;
	public int pi;
	Producteur ( T tampon, int pi){
		this.tampon=tampon;
		this.pi=pi;
		setName(pi+"");
		start();
	}
	public void run(){
		try {
			while(true){
				String m= "msg de prod"+currentThread().getName();
				tampon.produire(m) ;
				Thread.sleep(2000);
			}

		}catch (Exception e) {
			e.printStackTrace();
		}


	}
	public static void main(String[] args) {
		int port = 4020;
		InetAddress hote = null;
		Socket sc = null;
		PrintWriter out;
		try{
			if(args.length >= 2){
				hote = InetAddress.getByName(args[0]);
				port = Integer.parseInt(args[1]);
			}
			else{
				hote = InetAddress.getLocalHost();
			}
		}
		catch(UnknownHostException e){
			System.err.println("Machine inconnue" + e);
		}

		try{
			sc = new Socket(hote, port);
			System.out.println("Ajout Producteur");
			out =new PrintWriter(sc.getOutputStream(),true);
			String req="Producteur";
			out.println(req);
			
		}
		catch(IOException e){
			System.err.println("Impossible de creer le socket de client :" + e);
		}
		finally{
			try{
				sc.close();
			}
			catch(IOException e){}
		}
	}

}
