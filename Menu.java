import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * La classe <code>Menu</code> permet de créer le menu principal lors du démarrage du jeu.
 *  

 */
public class Menu extends JFrame implements ActionListener {
	
	
	
	/**
	 * Le constructeur menu crée et affiche la fenétre du menu qui  implémente 2 boutons :
	 * Commencer une nouvelle partie  ; Quitter le jeu.
	 */
	public Menu() {
		this.setTitle("Démineur");
		Dimension dimension = new Dimension(250, 215);
		this.setSize(dimension);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JPanel menu = new JPanel();
		menu.setLayout(new FlowLayout(FlowLayout.CENTER));
	
		
		JLabel l_demineur = new JLabel("Démineur");
		l_demineur.setFont(new Font("Lucida Grande", 1, 20));
		l_demineur.setHorizontalAlignment(JLabel.CENTER);
		menu.add(l_demineur);
		
		JButton b_nvellePartie = new JButton("Commencer une nouvelle partie");
		JButton b_quitterJeu = new JButton("Quitter le jeu");
		
		
		b_nvellePartie.addActionListener(this);
		b_quitterJeu.addActionListener(this);
		
		menu.add(b_nvellePartie);
		menu.add(b_quitterJeu);
		this.add(menu);
		this.setVisible(true);
	}
	
	/**
	 * La méthode actionPerformed() est appelée lors d'un clic sur un des 2 boutons du menu.
	 * 
	 * @param e Ce paramétre décrit la source et les circonstances de l'événement.
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "Commencer une nouvelle partie") {
			ChoixPartie partie = new ChoixPartie(this);
			partie.parametres();
		}
		
		else if(e.getActionCommand() == "Quitter le jeu") {
			System.exit(0);
		}
	}
	
}