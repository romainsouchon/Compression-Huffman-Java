public class Main {

	public static void main(String[] args) {
		// Selection du texte � compresser
		String nom_f = "extraitalice.txt";
		Fichier texte = new Fichier(nom_f);
		// Affichage du texte � compresser
		System.out.println(texte.txt);
		texte.operation();
	}
}
