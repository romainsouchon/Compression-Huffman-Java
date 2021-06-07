
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Selection du texte à compresser
		String nom_f = "textesimple.txt";
		Fichier texte = new Fichier(nom_f);
		// Affichage du texte a traduire
		System.out.println(texte.txt);
		texte.operation();
		
	}
}
