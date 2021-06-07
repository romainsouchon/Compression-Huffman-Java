
public class Node {

	public int frequence;
	private char carac;
	private Node filsD = null;
	private Node filsG = null;
	private String BinCode = "";
	
	public Node(int frequence, char carac, Node filsG, Node filsD) {
		this.frequence = frequence;
		this.carac = carac;
		this.filsD = filsD;
		this.filsG = filsG;
	}

	public int getFrequence() {
		return frequence;
	}
	public void setFrequence(int frequence) {
		this.frequence = frequence;
	}

	public char getCarac() {
		return carac;
	}
	public void setCarac(char carac) {
		this.carac = carac;
	}

	public Node getFilsD() {
		return filsD;
	}
	public void setFilsD(Node filsD) {
		this.filsD = filsD;
	}

	public Node getFilsG() {
		return filsG;
	}
	public void setFilsG(Node filsG) {
		this.filsG = filsG;
	}
	
	public String getBinCode() {
		return BinCode;
	}
	public void setBinCode(String binCode) {
		BinCode = binCode;
	}

	//Renvoie True si le noeud n'a ni de fils droit, ni de fils gauche. Sinon false.
	public boolean isLeaf() {
		return(this.filsG == null && this.filsD == null);
	}
	
	public String toString() {
		return "[" + carac + " " + frequence + ", Fg=" + filsG + ", Fd=" + filsD + "]";
	}
}