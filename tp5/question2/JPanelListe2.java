package question2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

public class JPanelListe2 extends JPanel implements ActionListener, ItemListener {

    private JPanel cmd = new JPanel();
    private JLabel afficheur = new JLabel();
    private JTextField saisie = new JTextField();

    private JPanel panelBoutons = new JPanel();
    private JButton boutonRechercher = new JButton("rechercher");
    private JButton boutonRetirer = new JButton("retirer");

    private CheckboxGroup mode = new CheckboxGroup();
    private Checkbox ordreCroissant = new Checkbox("croissant", mode, false);
    private Checkbox ordreDecroissant = new Checkbox("décroissant", mode, false);

    private JButton boutonOccurrences = new JButton("occurrence");

    private JButton boutonAnnuler = new JButton("annuler");

    private static TextArea texte = new TextArea();

    private static List<String> liste;
    private static Map<String, Integer> occurrences;
	private static Stack<List<String>> states;
    public JPanelListe2(List<String> liste, Map<String, Integer> occurrences) {
        this.liste = liste;
        this.occurrences = occurrences;
		states = new Stack<List<String>>();
        cmd.setLayout(new GridLayout(3, 1));

        cmd.add(afficheur);
        cmd.add(saisie);

        panelBoutons.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelBoutons.add(boutonRechercher);
        panelBoutons.add(boutonRetirer);
        panelBoutons.add(new JLabel("tri du texte :"));
        panelBoutons.add(ordreCroissant);
        panelBoutons.add(ordreDecroissant);
        panelBoutons.add(boutonOccurrences);
        panelBoutons.add(boutonAnnuler);
        cmd.add(panelBoutons);


        if(liste!=null && occurrences!=null){
            afficheur.setText(liste.getClass().getName() + " et "+ occurrences.getClass().getName());
            texte.setText(liste.toString());
        }else{
            texte.setText("la classe Chapitre2CoreJava semble incomplète");
        }

        setLayout(new BorderLayout());

        add(cmd, "North");
        add(texte, "Center");

        boutonRechercher.addActionListener(this);
        boutonRetirer.addActionListener(this);
		boutonOccurrences.addActionListener(this);
		ordreCroissant.addItemListener(this);
		ordreDecroissant.addItemListener(this);
        boutonAnnuler.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(!states.isEmpty())
					annuler();
			}
		});

    }

    public void actionPerformed(ActionEvent ae) {
        try {
            boolean res = false;
            if (ae.getSource() == boutonRechercher || ae.getSource() == saisie) {
                res = liste.contains(saisie.getText());
                Integer occur = occurrences.get(saisie.getText());
                afficheur.setText("résultat de la recherche de : "
                    + saisie.getText() + " -->  " + res);
            } else if (ae.getSource() == boutonRetirer) {
				List<String> s = new LinkedList<String>();
				s.addAll(liste);
				states.push(s);
                res = retirerDeLaListeTousLesElementsCommencantPar(saisie
                    .getText());
                afficheur.setText("résultat du retrait de tous les éléments commençant par -->  "
                    + saisie.getText() + " : " + res);
            } else if (ae.getSource() == boutonOccurrences) {
                Integer occur = occurrences.get(saisie.getText());
                if (occur != null)
                    afficheur.setText(" -->  " + occur + " occurrence(s)");
                else
                    afficheur.setText(" -->  0 ");
            }
            texte.setText(liste.toString());

        } catch (Exception e) {
            afficheur.setText(e.toString());
        }
    }

    public void itemStateChanged(ItemEvent ie) {
        LinkedList<String> temp=null;
		if (ie.getSource() == ordreCroissant){
			List<String> s = new LinkedList<String>();
			s.addAll(liste);
			states.push(s);
			Collections.sort(liste);
		}
            
        else if (ie.getSource() == ordreDecroissant){
			List<String> s = new LinkedList<String>();
			s.addAll(liste);
			states.push(s);
			Collections.sort(liste,new listComparator());
		}

        texte.setText(liste.toString());
    }

    private boolean retirerDeLaListeTousLesElementsCommencantPar(String prefixe) {
        boolean resultat = false;
		Iterator<String> it=liste.iterator();
		while(it.hasNext()){
			if(it.next().startsWith(prefixe)){
				resultat=true;
				it.remove();
			}
		}
		occurrences = Chapitre2CoreJava2.occurrencesDesMots(liste);
		texte.setText(liste.toString());
        return resultat;
    }
	
	private class listComparator<String> implements Comparator<String>{
		public int compare(String s1,String s2){
			return ((Comparable)s2).compareTo((Comparable)s1);
		}
	}
	
	private static final void annuler(){
		liste=states.pop();
		texte.setText(liste.toString());
		occurrences = Chapitre2CoreJava2.occurrencesDesMots(liste);
	}

}