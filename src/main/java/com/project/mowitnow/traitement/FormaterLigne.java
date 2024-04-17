package com.project.mowitnow.traitement;

import com.project.mowitnow.entites.Coordonnees;
import com.project.mowitnow.entites.Params.InstructionTondeuse;
import com.project.mowitnow.entites.Params.Orientation;
import com.project.mowitnow.entites.Pelouse;
import com.project.mowitnow.entites.PositionTondeuse;

import java.util.ArrayList;
import java.util.List;

public class FormaterLigne {

	private static final String CHAINE_ESPACE = " ";

	private FormaterLigne(){

	}

	/**
	 * récuperer la position de la tondeuse qui est définit par ses coordonnées et son orientation
	 * @param ligneTondeuse : ligne de la position de la tondeuse ( ex : 2 3 G)
	 * @return l'objet qui définit la position dela tondeuse
	 */
	public static PositionTondeuse formaterLigneTondeuse(String ligneTondeuse){
		String[] elts = ligneTondeuse.split(CHAINE_ESPACE);
		Coordonnees pCoordonneesTondeuse = new Coordonnees(Integer.valueOf(elts[0]), Integer.valueOf(elts[1]));
		Orientation orientationTondeuse = getOrientation(elts[2].charAt(0));
		return new PositionTondeuse(pCoordonneesTondeuse, orientationTondeuse);
	}

	/**
	 * récuperer l'objet Pelouse contenant les coordonnées limites de la pelouse
	 * @param lignePelouse : ligne de la pelouse ( ex : 2 3)
	 * @return l'objet qui définit la limite de la pelouse
	 */
	public static Pelouse formaterLignePelouse(String lignePelouse){
		String[] elts = lignePelouse.split(CHAINE_ESPACE);
		return new Pelouse(new Coordonnees(Integer.valueOf(elts[0]), Integer.valueOf(elts[1])));
	}

	/**
	 * récuperer une liste d'enum InstructionTondeuse correspondante à la ligne d'instruction
	 * @param ligneInstruction : suite d'instruction ( ex : GDAGD)
	 * @return une liste d'enum InstrctionTondeuse
	 */
	public static List<InstructionTondeuse> formaterLigneInstruction(String ligneInstruction){
		List<InstructionTondeuse> listInstruction = new ArrayList<InstructionTondeuse>();
		for(char instruction :ligneInstruction.toCharArray()){
			listInstruction.add(getInstruction(instruction));
		}
		return listInstruction;
	}

	/**
	 * récuperer un enum Orientation correspondant au caractère de l'orientation
	 * @param cOrientation : caractère de l'orientation (E, W, N, S)
	 * @return l'enum correspondant à l'orientation 
	 */
	public static Orientation getOrientation(char cOrientation){
		for(Orientation orientation : Orientation.values()) {
			if (orientation.getCodeOrientation() == cOrientation){
				return orientation;
			}
		}
		return null;
	}

	/**
	 * récuperer un enum InstructionTondeuse correspondant au caractère d'instrction
	 * @param cInstruction : caractère de l'instruction (A, G, D)
	 * @return l'enum correspondant à l'instruction 
	 */
	public static InstructionTondeuse getInstruction(char cInstruction){
		for(InstructionTondeuse instruction : InstructionTondeuse.values()) {
			if (instruction.getCodeInstruction() == cInstruction) {
				return instruction;
			}
		}
		return null;
	}
}