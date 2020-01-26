import java.net.*;
import java.io.*;

public class Serveur{
	public static void main(String[] args) {
		int port = 4020;
		ServerSocket se;
		Socket ssv = null;
		BufferedReader in;
		int nbP=0;
		int nbC=0;
		T tampon = new T(5);
		
		if (args.length==1){
			System.out.println("T de taille "+Integer.parseInt(args[0]));
			tampon = new T(Integer.parseInt(args[0]));
		}
		else{
			System.out.println("T de taille 5");
			tampon= new T(5);
		}
		try{
			se = new ServerSocket(port);
			System.out.println("Site T à l'écoute");
			System.out.println("En attente de producteur ou consommateur");
			while(true){
				ssv = se.accept();
				System.out.println("Connexion etablie");
				in =new BufferedReader(new InputStreamReader(ssv.getInputStream()));
				String rep= in.readLine();
				if(rep.equals("Producteur")) {
					nbP++;
					Producteur p = new Producteur(tampon,nbP);
				}else if (rep.equals("Consommateur")) {
					nbC++;
					Consommateur c =new Consommateur(tampon,nbC);
					
				}
			}

		}
		catch(IOException e){
			System.err.println("Erreur");
		}
		finally{
			try{
				ssv.close();
			}
			catch(IOException e){}
		}
	}
}