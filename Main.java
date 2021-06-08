public class Main {

	public static void main(String[] args) {
		// Selection du texte à compresser
		String nom_f = "extraitalice.txt";
		Fichier texte = new Fichier(nom_f);
		// Affichage du texte à compresser
		System.out.println(texte.txt);
		texte.operation();
	}
}
