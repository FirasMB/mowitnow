package com.project.mowitnow.entites;


public class Params {
	
	private Params(){
		
	}
	
	/**
	 * orientations possibles
	 * @author sgmira
	 *
	 */
	public static enum Orientation {
		NORTH('N', "Nord"),
		EAST('E', "est"),
		WEST('W', "ouest"),
		SOUTH('S', "sud");
		
		private char codeOrientation;
		private String libelleOrientation;
		
		private Orientation(char pCodeOrientation, String pLibelleOrientation){
			this.codeOrientation = pCodeOrientation;
			this.libelleOrientation = pLibelleOrientation;
		}
		public char getCodeOrientation() {
			return codeOrientation;
		}
		public String getLibelleOrientation() {
			return libelleOrientation;
		}
	}
	
	/**
	 * instructions possibles
	 * @author sgmira
	 *
	 */
	public static enum InstructionTondeuse{
		DROITE('D', "Pivoter à droite"),
		GAUCHE('G', "Pivoter à gauche"),
		AVANCER('A', "Avancer");
		
		private String libelleInstruction;
		private char codeInstruction;
		
		private InstructionTondeuse(char pCodeInstruction, String libelleInstruction) {
			this.libelleInstruction = libelleInstruction;
			this.codeInstruction = pCodeInstruction;
		}
		
		public String getLibelleInstruction() {
			return libelleInstruction;
		}
		public char getCodeInstruction() {
			return codeInstruction;
		}

	}


	public static final String ERREUR_DONNEES_INCORRECTES= "données incorrectes";
	public static final String ERREUR_FICHIER_INEXISTANT= "fichier inexistant";
	public static final String ORIENTATION_INCORRECTE = "orientation incorrecte";
	public static final String INSTRUCTION_INCORRECTE = "instruction incorrecte";
	public static final String POSITION_INCORRECTE = "position incorrecte";
}