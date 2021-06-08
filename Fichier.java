import java.io.*;
import java.util.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Fichier {
	float osize;
	float msize;
	
	String txt;
	String nom;
	String CodeBin = "";
		
	TreeSet<Tuple> tuples = new TreeSet<Tuple>();
	
	ArrayList<Character> alphabet = new ArrayList<Character>();
	ArrayList<Integer> frequence = new ArrayList<Integer>();
	ArrayList<Node> listNode = new ArrayList<Node>();
	
	HashMap<Character, String> dico = new HashMap<Character, String >();
	
	
	public Fichier(String fichier) {
		this.nom = fichier;
		this.txt = this.open("Textes\\" + fichier);
	}
	/*
	 * Ouvre et lis le fichier, ajoute tous les caractères dans texte
	 */
	public String open(String adresse){
		
		String texte = "";
		try {
		      File myObj = new File(adresse);
		      this.osize = myObj.length();
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        texte += data;
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		return texte;

	}
	
	/*
	 * Crée les listes en lisant caractère par caractère et ajoute le nombre d'ocurrence
	 */
	public void lecture() {
		for(int i=0; i<this.txt.length()-1; i++){
			if (this.alphabet.contains(this.txt.charAt(i)) == false){
				/*
				 * Si l'alphabet ne contient pas le nouvel élément il l'ajoute aux alphabets et ajoute '1' aux liste de fréquence
				 */
			 	Character str = this.txt.charAt(i);
			 	this.alphabet.add(str);
			 	this.frequence.add(1);
			}else{
				/*
				 * Sinon ajoute 1 aux valeur correspondant à l'élement rechercher
				 */
			 	int rang = alphabet.indexOf(this.txt.charAt(i));
			 	this.frequence.set(rang, this.frequence.get(rang)+1);
			}
			
		}
	}
	
	/*
	 * Cree tous les tuples, et les ajoutes dans le Treeset
	 * Ils sont automatiquement trier grace à la fonction
	 * compareTo dans la classe Tuple
	 */
	public void creaTreeSet() {
		for(int f = 0; f < this.frequence.size(); f++) {
			this.tuples.add(new Tuple(this.frequence.get(f),this.alphabet.get(f)));
		}
	}
	
	/*
	 * Cree tous les noeuds
	 * clone le TreeSet, pour chaque element on y cree un noeud, l'ajoute a la liste des noeuds
	 * et supprime l'element du treeset
	 * A faire jusqu'a que le TreeSet soit vide (size = 0)
	 */
	public ArrayList<Node> creaNode() {
		while(tuples.size() > 0) {
			int f = tuples.first().frequence;
			char c = tuples.first().caractere;
			tuples.remove(tuples.first());
			this.listNode.add(new Node(f,c,null,null));
		}
		return this.listNode;
	}
	
	// Fonction qui ecrit dans le fichier Freq.txt, le nombre de caractère, chaque caractère ainsi que son nombre d'apparition.
	public void write_txt() {
		try {
			String nom = this.nom.substring(0, this.nom.length()-4);
			File file = new File("Textes\\Freq_"+nom+".txt");

			// créer le fichier s'il n'existe pas
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(Integer.toString(this.txt.length()));
			   
			for (int i = 0; i < this.listNode.size(); i++) {
				bw.newLine();
				String carac = Character.toString(this.listNode.get(i).getCarac());
				String freq = Integer.toString(this.listNode.get(i).getFrequence());
				String ligne = carac + " " + freq;
				bw.write(ligne);
			}
			bw.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Node creationArbre() {
		while(listNode.size() > 1) {
			Node nodeG = listNode.get(0);
			Node nodeD = listNode.get(1);
			listNode.add(new Node(nodeG.frequence+nodeD.frequence,'^',nodeG,nodeD));
			/*Creation du noeud qui ajoute les deux frequences les plus faiblex */
			listNode.remove(0);
			listNode.remove(0);
			/*Supprime les deux premier noeuds qui ont permis la création de celui du dessus*/
			this.Nodetri(listNode);
			/*Tri La liste des noeuds afin de remettre le bonne ordre des fréquences*/
		}
		return listNode.get(0);
	}
	/*Fonction qui tri une liste donnée en argument par ordre de fréquence.*/
	public void Nodetri(ArrayList<Node> list) {
		for (int i = 1; i < list.size(); i++) {
			int frequence = list.get(i).getFrequence();
			char carac = list.get(i).getCarac();
			Node fd = list.get(i).getFilsD();
			Node fg = list.get(i).getFilsG();
			String binCode = list.get(i).getBinCode();			
			int j = i;
			while(j > 0 && list.get(j-1).getFrequence() > frequence) {
				list.get(j).setFrequence(list.get(j-1).getFrequence());
				list.get(j).setCarac(list.get(j-1).getCarac());
				list.get(j).setFilsD(list.get(j-1).getFilsD());
				list.get(j).setFilsG(list.get(j-1).getFilsG());
				list.get(j).setBinCode(list.get(j-1).getBinCode());
				j--;					
			}
			list.get(j).setFrequence(frequence);
			list.get(j).setCarac(carac);
			list.get(j).setFilsD(fd);
			list.get(j).setFilsG(fg);
			list.get(j).setBinCode(binCode);
		}
	}
	
	/*Fonction qui parcours l'arbre en profondeur afin d'ajouter le code binaire necessaire*/
	public void profondeur(Node node, String codeB){		
		if (node.isLeaf()) 
			this.dico.put(node.getCarac(),codeB);
		
		if (node.getFilsG() != null) 
			if (node.getBinCode() =="") 
				this.profondeur(node.getFilsG(), codeB + "0");
		/*Si fils gauche, ajouter 0 au BinCode*/
		if (node.getFilsD()!= null) 
			if(node.getBinCode()=="") 
				this.profondeur(node.getFilsD(), codeB + "1");	
		/*Si fils droit, ajouter 1 au BinCode*/
	}
	
	// Fonction qui crée le conde binaire en concaténant le code binaire de chaque caractère
	public void binary() {
		for (char i: this.txt.toCharArray()) {
			String code = this.dico.get(i);
			this.CodeBin += code;
		}
	}
	
	// Fonction écrivant le code binaire dans le fichier CodeBin présent dans le dossier Textes
	public void write_bin() {
		try {
			String nom = this.nom.substring(0, this.nom.length()-4);
			
			FileOutputStream fos = new FileOutputStream(new File("Textes\\CodeBin_" + nom + ".bin"));
		    BufferedOutputStream writer = new BufferedOutputStream(fos);
		    			
			while (this.CodeBin.length() % 8 != 0) {
				this.CodeBin += "0";
			}
			
			for(int i = 0; i < this.CodeBin.length()-2; i = i + 8) {
				String part = this.CodeBin.substring(i, i+8);
				int code = 0;
				for(int j = 0; j<part.length(); j+=1) {
					if (part.charAt(j) == '1'){
						code += Math.pow(2, 7-j);
					}
				}
				writer.write(code);
				writer.flush();
			}			
			writer.close();
			File myObj = new File("Textes\\CodeBin_" + nom + ".bin");
			this.msize = myObj.length();
			
			
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Calcul du taux de compression à l'aide de la fonction donnée dans le sujet
	public void taux() {
		float taux = 1 - (this.msize/this.osize);
		System.out.println("Le taux de compression est de " + taux + ".Soit " + taux*100 + "%.");
	}
	
	// fonction faisant appel à chaque fonction dans l'ordre afin de traiter la compression
	public void operation() {
		this.lecture();
		this.creaTreeSet();
		this.creaNode();
		this.write_txt();
		this.profondeur(this.creationArbre(), ""); /*Code binaire de base vide -> ""*/
		this.binary();
		this.write_bin();
		System.out.println("Le code binaire est disponible dans le dossier Textes.\n");
		this.taux();
	}

}
