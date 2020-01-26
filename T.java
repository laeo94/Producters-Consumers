public class T {
	//Capacite du tampon
	public int N;
	//Tableau des messages produits par les producteurs
	public  String t[];
	//indice d'insertion dans le tampon
	public  int in=0;
	//indice d'extraction dans le tampon
	public  int out=0;
	//nombre de message stockés dans t et non encore consommés 
	public int nbmess=0;
	//nombre de cellules liberées entre deux passage du jeton
	public  int nbcell=0;

	public T(int N) {
		this.N=N;
		this.t=new String[N];
	}
	public synchronized void consommer () throws InterruptedException{
		while(nbmess == 0 ){
			System.out.println("Tampon vide attendre");
			wait();
		}
		String m= t[out];
		out = (out + 1)%N;
		nbmess--;
		nbcell++;
		System.out.println("C "+Thread.currentThread().getName()+" a consommé : " + m);
		notifyAll();
	}
	public synchronized void produire (String m)throws InterruptedException{
		while(nbmess == N ){
			System.out.println("Tampon plein attendre");
			wait();
		}
		t[in]=m;
		in = (in  + 1)% N;
		nbmess++;
		notifyAll();
		System.out.println("P "+Thread.currentThread().getName()+ " a produit: " + m);

	}
}
