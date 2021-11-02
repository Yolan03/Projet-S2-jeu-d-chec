
import java.util.*;
public class Plateau {
	
	ArrayList<Case> tabCase= new ArrayList<Case>();		//case rang� par x puis y
	
	
	public Plateau ()
	{
		for (int i=1;i<=8;i++)
		{
			for (int l=1;l<=8;l++)
			{
				Position p=new Position(l,i);
				Case c =new Case(p.clone());
				tabCase.add(c);		
			}
			
		}
	}
	
	public void initialisation() {
		for (Piece count:Piece.tabPiece)
		{
			
			for (Case carre:tabCase) {
				if (count.position.equals(carre.getPosition())){
						carre.positionne(count);
						break;
				}
			}
		}
	}
	
	public void afficherEchiquier() {
		List<String> axeY =  new ArrayList<String>();
		axeY.add("A");
		axeY.add("B");
		axeY.add("C");
		axeY.add("D");
		axeY.add("E");
		axeY.add("F");
		axeY.add("G");
		axeY.add("H");
		
		int y = 0;
		while(y < 8){
			System.out.print(""+ axeY.get(y) + "  ");
			y++;
		}
		
		System.out.println("");
		y = 1;
		while(y <= 8){
			System.out.print("---");
			y++;
		}
		System.out.println("");
		y = 1;
		int i=1;
		
		for (Case count:tabCase)
		{
			

			if (i!=count.getPosition().getY()) {	// fais passer a la ligne
				

				System.out.println(y);

				y++;
				i++;
			}
			
			if (count.getPiece()!=null) {
				
				Piece p =count.getPiece();
				String coul;
				if (p.getCouleur().equals(Couleur.WHITE))
					coul="b";
				else
					coul="n";

					if (coul == "b") {
						switch(p.getClass().getSimpleName()) {
						
						case "Cavalier" :
							System.out.print("♞ |");
							break;
						case "Reine" :
							System.out.print("♛ |");
							break;
						case "Fou" :
							System.out.print("♝ |");
							break;
						case "Pion" :
							System.out.print("\u265F |");
							break;
						case "Roi" :
							System.out.print("♚ |");
							break;
						case "Tour" :
							System.out.print("♜ |");
							break;
					}
				}else {
					switch(p.getClass().getSimpleName()) {
					
					case "Cavalier" :
						System.out.print("♘ |");
						break;
					case "Reine" :
						System.out.print("♕ |");
						break;
					case "Fou" :
						System.out.print("♗ |");
						break;
					case "Pion" :
						System.out.print("♙ |");
						break;
					case "Roi" :
						System.out.print("♔ |");
						break;
					case "Tour" :
						System.out.print("♖ |");
						break;
					}
				}	
			
			}else {
				
				System.out.print("\u25FB |");
			}
			
				
		}
		System.out.println("" + y);
		
		y = 1;
		while(y <= 8){
			System.out.print("---");
			y++;
		}

		System.out.println("");
		y = 0;
		while(y < 8){
			System.out.print(""+ axeY.get(y) + "  ");
			y++;
		}
		System.out.println("");
	}	
	public void update()
	{
		this.initialisation();
		this.afficherEchiquier();
	}
	
	
	public Case getCase(int x,int y)
	 {
		int entree=(y-1)*8+x-1;
		return this.tabCase.get(entree);
	 }
}
