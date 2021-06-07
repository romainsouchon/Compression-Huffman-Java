import java.util.ArrayList;
import java.util.TreeSet;

public class Tuple implements Comparable<Object>{
	
	char caractere;
	Integer frequence;
	
	public Tuple (Integer f, char c) {
		this.caractere = c;
		this.frequence = f;
		
	}
	

	/*
	 * Si la frequence du nouveau est inferieur a celle en parametre
	 * alors return -1 donc placé avant, si superieur return 1 donc placé apres
	 * si egale compare les codes ascii du caractere
	 */
	/*
	 * !!!! Fonction necessaire pour la création du TreeSet<Tuple> !!!!
	 */
	@Override
	public int compareTo(Object o) {
		if( o instanceof Tuple) {
			Tuple t = (Tuple)o;
			
			if(this.frequence<t.frequence) {
				return -1;					
			}
			
			if(this.frequence>t.frequence) {
				return 1;
			}
			
			if(this.frequence.equals(t.frequence)) {
				if((int)this.caractere<(int)t.caractere){
					return -1;				
				}
				if ((int)this.caractere>(int)t.caractere) {
					return 1;
				}
			}
		}
		return 0;
	}
	
	public char getCaractere() {
		return caractere;
	}

	public Integer getFrequence() {
		return frequence;
	}
	
	public String toString() {
		return this.caractere +" "+ this.frequence;
	}
	
}
