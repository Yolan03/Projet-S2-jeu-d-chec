import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import java.util.List;
import java.io.Serializable;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;



public class Partie extends JFrame implements Serializable{
	private Joueur joueurActuel;
	private int numTour=1;
	ArrayList <Joueur> listeJoueurs = new ArrayList <Joueur> ();
	Plateau plateau;
	private Scanner entree = new Scanner(System.in);          
	
	static  HashMap<String, Integer> AxeY = new HashMap<String, Integer>();
	static  HashMap<Integer,String> ReadAxeY = new HashMap<Integer,String>();
 	
	
	
	boolean fini;





	public Partie(String n1,String n2)
	{
		Joueur j1=new Joueur (n1,Couleur.WHITE);
		Joueur j2=new Joueur (n2,Couleur.BLACK);
		LoadAxeY();
		ReadLoadAxeY();
		listeJoueurs.add(j1);
		listeJoueurs.add(j2);
		initialisation();


		j1.updateTab();
		j2.updateTab();
		fini=false;
		Piece.updateAll();
	}

	private void LoadAxeY(){
		AxeY.put("A", 0);
		AxeY.put("B", 1);
		AxeY.put("C", 2);
		AxeY.put("D", 3);
		AxeY.put("E", 4);
		AxeY.put("F", 5);
		AxeY.put("G", 6);
		AxeY.put("H", 7);
		
		return;
	}

	private void ReadLoadAxeY(){
		ReadAxeY.put(0,"A");
		ReadAxeY.put(1,"B");
		ReadAxeY.put(2,"C");
		ReadAxeY.put(3,"D");
		ReadAxeY.put(4,"E");
		ReadAxeY.put(5,"F");
		ReadAxeY.put(6,"G");
		ReadAxeY.put(7,"H");
		
		return;
	}

	

	private void initialisation()
	{

		String w="";
		do{
			System.out.println("Lancer en mode\n| normal | echec et mat | Charger |");
			w = entree.nextLine();
		}
		while(!w.equals("normal")&&!w.equals("echec et mat")&&!w.equals("Charger"));

		if (w.equals("normal"))
		{
			init_pion();
			init_fou();
			init_tour();
			init_cavalier();
			init_royaux();
		}
		else if(w.equals("Charger")){
			load_init();
		}
		else if ( w.equals("echec et mat")){
			echecMat_init();
		}
		Plateau p = new Plateau();
		plateau=p;
		plateau.initialisation();
	}


	public Joueur getJoueurActuel() {
		return joueurActuel;
	}


	public int getNumTour() {
		return numTour;
	}


	public ArrayList<Joueur> getListeJoueurs() {
		return listeJoueurs;
	}


	public Plateau getPlateau() {
		return plateau;
	}


	public Scanner getEntree() {
		return entree;
	}


	public boolean isFini() {
		return fini;
	}


	public void perso_init()
	{
		String x="";
		do{

			x = entree.next();
		}while (x=="");


	}

	private ArrayList<Joueur> charger(){

		ArrayList<Joueur> Joueurs = new ArrayList<Joueur>();
		String path = getpath();
		try{
			FileInputStream file = new FileInputStream( path +"\\save");
			ObjectInputStream sortie = new ObjectInputStream(file);
			Joueurs = (ArrayList<Joueur>) sortie.readObject();
			sortie.close();
			file.close();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return Joueurs;
	}

	private void load_init(){
		ArrayList<Joueur> Joueurs = charger();
		Joueur joueur1 = Joueurs.get(0);
		Joueur joueur2 = Joueurs.get(1);
		
		int i = 0;
		while ( i < joueur1.tab.size()){
			
			if(joueur1.tab.get(i) instanceof Pion){
				Pion newPiece = (Pion) joueur1.tab.get(i);
				Couleur Color = newPiece.getCouleur();
				Position coord = newPiece.getPosition();
				int x = coord.getX();
				int y = coord.getY();
				Pion p = new Pion(x,y,Color);
			}else if(joueur1.tab.get(i) instanceof Fou){
				Fou newPiece = (Fou) joueur1.tab.get(i);
				Couleur Color = newPiece.getCouleur();
				Position coord = newPiece.getPosition();
				int x = coord.getX();
				int y = coord.getY();
				Fou fou = new Fou(x,y,Color);
			}else if(joueur1.tab.get(i) instanceof Cavalier){
				Cavalier newPiece = (Cavalier) joueur1.tab.get(i);
				Couleur Color = newPiece.getCouleur();
				Position coord = newPiece.getPosition();
				int x = coord.getX();
				int y = coord.getY();
				Cavalier p = new Cavalier(x,y,Color);
			}else if(joueur1.tab.get(i) instanceof Tour){
				Tour newPiece = (Tour) joueur1.tab.get(i);
				Couleur Color = newPiece.getCouleur();
				Position coord = newPiece.getPosition();
				int x = coord.getX();
				int y = coord.getY();
				Pion p = new Pion(x,y,Color);
			}else if(joueur1.tab.get(i) instanceof Reine){
				Reine newPiece = (Reine) joueur1.tab.get(i);
				Couleur Color = newPiece.getCouleur();
				Position coord = newPiece.getPosition();
				int x = coord.getX();
				int y = coord.getY();
				Pion p = new Pion(x,y,Color);
			}else if(joueur1.tab.get(i) instanceof Roi){
				Roi newPiece = (Roi) joueur1.tab.get(i);
				Couleur Color = newPiece.getCouleur();
				Position coord = newPiece.getPosition();
				int x = coord.getX();
				int y = coord.getY();
				Roi p = new Roi(x,y,Color);
			}
			i++;
		}
		i = 0;
		while ( i < joueur2.tab.size()){
			//Piece piece = joueur1.tab.get(i);
			if(joueur2.tab.get(i) instanceof Pion){
				Pion newPiece = (Pion) joueur2.tab.get(i);
				Couleur Color = newPiece.getCouleur();
				Position coord = newPiece.getPosition();
				int x = coord.getX();
				int y = coord.getY();
				Pion p = new Pion(x,y,Color);
			}else if(joueur2.tab.get(i) instanceof Fou){
				Fou newPiece = (Fou) joueur2.tab.get(i);
				Couleur Color = newPiece.getCouleur();
				Position coord = newPiece.getPosition();
				int x = coord.getX();
				int y = coord.getY();
				Fou fou = new Fou(x,y,Color);
			}else if(joueur2.tab.get(i) instanceof Cavalier){
				Cavalier newPiece = (Cavalier) joueur2.tab.get(i);
				Couleur Color = newPiece.getCouleur();
				Position coord = newPiece.getPosition();
				int x = coord.getX();
				int y = coord.getY();
				Cavalier cavalier = new Cavalier(x,y,Color);
			}else if(joueur2.tab.get(i) instanceof Tour){
				Tour newPiece = (Tour) joueur2.tab.get(i);
				Couleur Color = newPiece.getCouleur();
				Position coord = newPiece.getPosition();
				int x = coord.getX();
				int y = coord.getY();
				Tour p = new Tour(x,y,Color);
			}else if(joueur2.tab.get(i) instanceof Reine){
				Reine newPiece = (Reine) joueur2.tab.get(i);
				Couleur Color = newPiece.getCouleur();
				Position coord = newPiece.getPosition();
				int x = coord.getX();
				int y = coord.getY();
				Reine reine = new Reine(x,y,Color);
			}else if(joueur2.tab.get(i) instanceof Roi){
				Roi newPiece = (Roi) joueur2.tab.get(i);
				Couleur Color = newPiece.getCouleur();
				Position coord = newPiece.getPosition();
				int x = coord.getX();
				int y = coord.getY();
				Roi p = new Roi(x,y,Color);
			}
			i++;
		}
	}

	private void echecMat_init()
	{
		Tour t=new Tour(3,2,Couleur.BLACK);
		Tour t3=new Tour(2,3,Couleur.BLACK);
		Tour t2=new Tour(2,7,Couleur.BLACK);
		Roi r=new Roi(2,1,Couleur.WHITE);
		Roi r2=new Roi(2,5,Couleur.BLACK);
	}


	private void init_pion()
	{
		int x=1,y=2;
		for (x=1;x<=8; x++ )
		{
			Pion p=new Pion(x,y,Couleur.WHITE);
		}
		y=7;
		for (x=1;x<=8; x++ )
		{
			Pion p=new Pion(x,y,Couleur.BLACK);
		}
	}

	private void init_tour() {
		Tour t1=new Tour(1,8,Couleur.BLACK);
		Tour t2=new Tour(8,8,Couleur.BLACK);
		Tour t3=new Tour(1,1,Couleur.WHITE);
		Tour t4=new Tour(8,1,Couleur.WHITE);

	}
	private void init_cavalier() {
		Cavalier t1=new Cavalier(2,8,Couleur.BLACK);
		Cavalier t2=new Cavalier(7,8,Couleur.BLACK);
		Cavalier t3=new Cavalier(2,1,Couleur.WHITE);
		Cavalier t4=new Cavalier(7,1,Couleur.WHITE);

	}
	private void init_fou() {
		Fou t1=new Fou(3,8,Couleur.BLACK);
		Fou t2=new Fou(6,8,Couleur.BLACK);
		Fou t3=new Fou(3,1,Couleur.WHITE);
		Fou t4=new Fou(6,1,Couleur.WHITE);

	}
	private void init_royaux() {

		Reine r4=new Reine (5,1,Couleur.WHITE);
		Reine r2=new Reine (5,8,Couleur.BLACK);
		Roi r1= new Roi(4,8,Couleur.BLACK);
		Roi r3= new Roi(4,1,Couleur.WHITE);
	}


	public void lancer() {
		while(!fini){
			debutTour();
		}
	}



	public void finDuTour() {

		numTour++;
	}

	public void finDePartie() {
		System.out.println("Fini!");

		System.out.println("Voulez vous rejouez une partie ? (oui ou non)");
		String x=".";
		do{
			x = entree.nextLine();
		}
		while(x.equals(".") || !(x.equals("oui") || x.equals("non")));
		if (x.equals("oui"))
		{
			String y=".";
			do{
				System.out.println("Voulez vous changer de couleur? (oui ou non)");
				y = entree.nextLine();
			}
			while(y.equals(".") || !(y.equals("oui") || y.equals("non")));

			if(y.equals("oui")){
				recommencerPartie(2);
			}
			else if(y.equals("non")){
				recommencerPartie(1);
			}




		}
	}

	public void recommencerPartie(int n) {

		if (n==1)		//pas de changement de couleur
		{

			Piece.tabPiece.clear();
			Roi.tabRoi.clear();
			initialisation();fini=false;Piece.updateAll();
		}
		else if(n==2)	//changement de couleur des joueurs
		{
			Piece.tabPiece.clear();
			Roi.tabRoi.clear();
			Joueur j=this.listeJoueurs.get(0);
			listeJoueurs.set(0, listeJoueurs.get(1));
			listeJoueurs.set(1,j);
			initialisation();fini=false;Piece.updateAll();

		}

	}

	public void debutTour() { 	//lance le tour d'un des 2 joueurs
		this.update();
		if (!fini)
		{
			System.out.println("pour sauvegarder et quitter appuyer sur 1 sinon continuer avec 0 ?");
			int save = entree.nextInt();
			if (save == 1){
				save(listeJoueurs);
				System.exit(1);
			}
			if (numTour%2==1)
				joueurActuel=this.listeJoueurs.get(0);
			else
				joueurActuel=this.listeJoueurs.get(1);


			System.out.println("C'est au "+joueurActuel.getNom()+" de jouer");
			jouer(joueurActuel);
		}
		else
		{
			this.finDePartie();
		}

	}


	private int newtab(Joueur j){
		int coord = 0;
		//List<String[]> NewCord = new ArrayList<String[]>();
		
		int i = 0;
		while(i < j.tab.size()){
			Piece piece = j.tab.get(i);
			Position position = piece.getPosition();
			int X = position.getX();
			String stringX = ReadAxeY.get(X-1);
			String stringY= Integer.toString(position.getY()) ;
			System.out.print("["+ piece+" "+ stringX+ "|" + stringY + "]");
			i++;
		}
		System.out.println("");
		return coord;
	}

	private void jouer(Joueur j)
	{

		Position depart=null;
		Position arrivee=null;
		Piece selectionnee=null;
		Couleur jc=this.joueurActuel.getCouleur();

		

		
		boolean temp2=false;
		while (!temp2)										//si erreur sur pos arriv� tout recommencer
		{
			boolean temp=false;
			while (!temp)									
			{
				if (!Roi.getRoiCouleur(jc).isEchec())
				{
					newtab(j);
					//System.out.println(j.tab);
					depart=getEntree(1);						//recupere du user la pos de depart
					selectionnee=joueurActuel.bougerT1(depart);	
				}
				else
				{
					//System.out.println(Roi.getRoiCouleur(jc));
					depart=getEntree(1);
					selectionnee=joueurActuel.bougerRoiT1(depart);

				}
				if (selectionnee!=null)
					temp=true;
			}

			temp=false;
			arrivee=getEntree(2);							//recuperer pos arriv�e
			temp2=joueurActuel.bougerT2(selectionnee,depart,arrivee);	

		}
		Case caseTemp=plateau.getCase(depart.getX(), depart.getY());
		caseTemp.vider();
		System.out.println(caseTemp.getPosition());

		finDuTour();




	}

	public void save(ArrayList<Joueur> j){
		String path = getpath();
		try {
			
            FileOutputStream fileOut = new FileOutputStream(path + "//save");
            ObjectOutputStream sortie = new ObjectOutputStream(fileOut);
            sortie.writeObject(j);

            sortie.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
		}
	}

	private String getpath(){
		String path = dialog();
		return path;
	}

	private String dialog(){
		String path = "";
		JFileChooser choix = new JFileChooser();

		choix.setAcceptAllFileFilterUsed(false);
        choix.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int retour=choix.showOpenDialog(getParent());

		if(retour==JFileChooser.APPROVE_OPTION){
			path = choix.getSelectedFile().getAbsolutePath();

		 }else {}
		return path;
	}
	private Position getEntree(int n)
	{
		String temp=null;
		if(n==1)
			temp="Entrez la position de depart (x puis y)";
		if(n==2)
			temp="Entrez la position d'arriv�e (x puis y)";


		System.out.println(temp);
		
		String coord = "";
		String[] coordParse;
		int x= 0;
		int y=0;
		do{
			coord = entree.nextLine();
			coordParse = coord.split(" ");
			
			
			//y  = entree.nextInt();
		}
		while(coordParse.length != 2);
		x = AxeY.get(coordParse[0]) + 1;
		y = Integer.valueOf(coordParse[1]);
		System.out.println("Piece Choisie: " + coordParse[0] +"  "+y);
		Position pos=new Position(x,y);
		return pos;
	}



	private void update()
	{

		this.listeJoueurs.get(0).updateTab();
		this.listeJoueurs.get(1).updateTab();

		Piece.updateAll();
		plateau.update();

		for (Roi count:Roi.tabRoi)
		{
			String t;
			t=count.update();



			Couleur coul=count.getCouleur();
			if(count.echec && !count.echecEtMat)
				System.out.println("Le roi "+count.getCouleur()+ " est en echec");
			else if (count.echec && count.echecEtMat || count.echecEtPat)
			{
				fini=true;
				System.out.println("Le roi "+count.couleur+" est en "+t+"\n");
				if (coul.equals(Couleur.WHITE))
					System.out.print("Le joueur "+this.listeJoueurs.get(0));
				else
					System.out.print("Le joueur "+this.listeJoueurs.get(1));
				System.out.println(" a perdu");

			}

		}

	}


}
