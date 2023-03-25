
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusAdapter;
import javax.swing.*;

/**
 * La classe <code>ChoixPartie</code> permet de choisir le nombre de lignes, le nombre de colonnes
 * et le nombre de mines de la nouvelle partie.
 * 

 */
public class ChoixPartie extends FocusAdapter implements ActionListener {
	
	/**
	 * nombre de lignes de la grille par défaut.
	 */
	private int nbLignes = 9;
	
	/**
	 * nombre de colonnes de la grille par défaut.
	 */
	private int nbColonnes = 9;
	
	/**
	 * nombre de mines de la grille par défaut.
	 */
	private int nbMines = 10;
	
	/**
	 * Champ de texte permettant de saisir le nombre de lignes de la grille.
	 */
	private JTextField tf_nbLignes;
	
	/**
	 * Champ de texte permettant de saisir le nombre de colonnes de la grille.
	 */
	private JTextField tf_nbColonnes;
	
	/**
	 * Champ de texte permettant de saisir le nombre de mines de la grille.
	 */
	private JTextField tf_nbMines;
	
	/**
	 * Etiquette affichant le nombre de mines de la grille dynamiquement.
	 */
	private JLabel afficheNbMines;
	
	/**
	 * reponse enregistre la valeur de retour de la boéte de dialogue - contenant
	 * les 3 champs de texte - en fonction du bouton cliqué.
	 */
	private int reponse;
	
	/**
	 * reponse enregistre la valeur de retour de la méthode isTextfieldValueNumber().
	 */
	private boolean isNumber;
	
	/**
	 * Fenétre de la classe menu permettant l'affichage des paramétres de la grille é entrer dans une boéte de dialogue.
	 */
	private JFrame fenetre;
	
	/**
	 * Constructeur ChoixPartie définissant la récupération de la fenétre de la classe menu pour l'affichage
	 * des paramétres de la grille é entrer dans une boéte de dialogue.
	 *
	 * @param fenetre Fenétre de la classe menu.
	 */
	public ChoixPartie(JFrame fenetre) {
		this.fenetre = fenetre;
	}
	
	/**
	 * La méthode Parametres() affiche la boéte de dialogue
	 * et fait des vérifications sur la validité des valeurs remplies.
	 */
	public void parametres() {
		tf_nbLignes = new JTextField(Integer.toString(nbLignes));
		tf_nbColonnes = new JTextField(Integer.toString(nbColonnes));
		tf_nbMines = new JTextField(Integer.toString(nbMines));
		
		
		
		JPanel p_radio = new JPanel();
		p_radio.setLayout(new GridLayout(0,2));
		
		JRadioButton r_facile = new JRadioButton("Débutant");
		JRadioButton r_moyen = new JRadioButton("Intermédiaire");
		JRadioButton r_difficile = new JRadioButton("Avancé");
		
		
		r_facile.addActionListener(this);
		r_moyen.addActionListener(this);
		r_difficile.addActionListener(this);
		
		ButtonGroup g_difficulte = new ButtonGroup();
		
		g_difficulte.add(r_facile);
		g_difficulte.add(r_moyen);
		g_difficulte.add(r_difficile);
		
		r_facile.setSelected(true);
		
		p_radio.add(r_facile);
		p_radio.add(r_moyen);
		p_radio.add(r_difficile);
		
		JPanel p_perso = new JPanel();
		p_perso.setLayout(new GridLayout(0,2));
		p_perso.add(new JLabel("Nombre de lignes (4 - 30) : "));
		p_perso.add(tf_nbLignes);
		p_perso.add(new JLabel("Nombre de colonnes (4 - 30) : "));
		p_perso.add(tf_nbColonnes);
		afficheNbMines = new JLabel("Nombre de mines (1 - " + nbLignes*nbColonnes + ") : ");
		p_perso.add(afficheNbMines);
		p_perso.add(tf_nbMines);
		
		tf_nbLignes.setEditable(false);
		tf_nbColonnes.setEditable(false);
		tf_nbMines.setEditable(false);
		
		tf_nbLignes.addFocusListener(this);
		tf_nbColonnes.addFocusListener(this);
		tf_nbMines.addFocusListener(this);
		
		JPanel panneau = new JPanel();
		panneau.setLayout(new GridLayout(2,0));
		panneau.add(p_radio);
		panneau.add(p_perso);
		
		do {
			reponse = JOptionPane.showConfirmDialog(
				null,
				panneau,
				"Paramétres",
				JOptionPane.DEFAULT_OPTION,
				JOptionPane.PLAIN_MESSAGE);
			
			isNumber = isTextfieldValueNumber();
			if (reponse == -1) { // Toutes les conditions sont vraies pour sortir de la boucle quand on ferme la boite de dialogue

				isNumber = true;
				nbLignes = 10;
				nbColonnes = 10;
				nbMines = 10;
			}
		} while (!isNumber || nbLignes < 4 || nbLignes > 30 || nbColonnes < 4 || nbColonnes > 30 || nbMines < 1 || nbMines > nbLignes*nbColonnes);
		
		if (reponse == JOptionPane.OK_OPTION) {
			valider();
		}
	}
	
	/**
	 * La méthode isTextfieldValueNumber() vérifie que les valeurs 
	 * remplies dans les champs de texte soient des nombres.
	 */
	private boolean isTextfieldValueNumber() {
		try {
			nbLignes = Integer.parseInt(tf_nbLignes.getText());
			nbColonnes = Integer.parseInt(tf_nbColonnes.getText());
			nbMines = Integer.parseInt(tf_nbMines.getText());
			return true;
		}
		catch (NumberFormatException e) {
			return false;
		}
	}
	
	/**
	 * La méthode Valider() est appelée une fois que toutes les vérifications
	 * sont validées et appelle la méthode Jeu pour démarrer la nouvelle partie.
	 */
	private void valider() {
		int fenetreH = nbLignes * 42; // Ajustement automatique de la fenétre en fonction du nombre de cases
		int fenetreL = nbColonnes * 55;
		
		// Obtenir la définition de l'écran
		Dimension definitionEcran = Toolkit.getDefaultToolkit().getScreenSize();
		int largeurEcran = (int) definitionEcran.getWidth();
		int hauteurEcran = (int) definitionEcran.getHeight();
		
		if(fenetreH > hauteurEcran - 50) fenetreH = hauteurEcran - 50;
		if(fenetreL > largeurEcran - 50) fenetreL = largeurEcran - 50;
		Jeu nouveauJeu = new Jeu(nbLignes, nbColonnes, nbMines, fenetreH, fenetreL);
		fenetre.dispose();
		nouveauJeu.afficher();
	}
	
	
	/**
	 * La méthode actionPerformed() est appelée lors d'un clic sur un des 3 boutons du menu.
	 * 
	 * @param e Ce paramétre décrit la source et les circonstances de l'événement.
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "Débutant") {
			parametresDifficulte("9", "9", "10");
		}
		else if(e.getActionCommand() == "Intermédiaire") {
			parametresDifficulte("16", "16", "40");
		}
		else if(e.getActionCommand() == "Avancé") {
			parametresDifficulte("16", "30", "99");
		}
		
	}
	
	
	/**
	 * La méthode parametresDifficulte() affiche le nombre de lignes, 
	 * de colonnes et de mines dans les champs de texte selon la difficulté.
	 * 
	 * @param nbLignes le nombre de lignes
	 * @param nbColonnes le nombre de colonnes
	 * @param nbMines le nombre de mines
	 */
	private void parametresDifficulte(String nbLignes, String nbColonnes, String nbMines) {
		tf_nbLignes.setText(nbLignes);
		tf_nbColonnes.setText(nbColonnes);
		tf_nbMines.setText(nbMines);
		
		tf_nbLignes.setEditable(false);
		tf_nbColonnes.setEditable(false);
		tf_nbMines.setEditable(false);
	}
	
	
	/**
	 * La méthode focusLost() est appelée lors d'une perte de focus d'un des champs.
	 * 
	 * @param e Ce paramétre décrit la source et les circonstances de l'événement.
	 */
	public void focusLost(FocusEvent e) {
		isNumber = isTextfieldValueNumber();
		if (isNumber && nbLignes >= 4 && nbLignes <= 30 && nbColonnes >= 4 && nbColonnes <= 30 && nbMines >= 1 && nbMines <= nbLignes*nbColonnes) {
			afficheNbMines.setText("Nombre de mines (1 - " + nbLignes*nbColonnes + ") : ");
		}
		else {
			afficheNbMines.setText("Nombre de mines (1 - *) : ");
		}
	}
}