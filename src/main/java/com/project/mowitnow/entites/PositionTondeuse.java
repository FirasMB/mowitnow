package com.project.mowitnow.entites;
public class PositionTondeuse {
	
	private Coordonnees coordonneesTondeuse;
	private Params.Orientation orientationTondeuse;

	public PositionTondeuse(Coordonnees pCoordonneesTondeuse,
			Params.Orientation pOrientationTondeuse) {
		this.coordonneesTondeuse = pCoordonneesTondeuse;
		this.orientationTondeuse = pOrientationTondeuse;
	}

	public Params.Orientation getOrientationTondeuse() {
		return orientationTondeuse;
	}

	public void setOrientationTondeuse(Params.Orientation pOrientationTondeuse) {
		this.orientationTondeuse = pOrientationTondeuse;
	}

	public Coordonnees getCoordonneesTondeuse() {
		return coordonneesTondeuse;
	}

	public void setCoordonneesTondeuse(Coordonnees pCoordonneesTondeuse) {
		this.coordonneesTondeuse = pCoordonneesTondeuse;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coordonneesTondeuse == null) ? 0 : coordonneesTondeuse.hashCode());
		result = prime * result + ((orientationTondeuse == null) ? 0 : orientationTondeuse .hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PositionTondeuse other = (PositionTondeuse) obj;
		if (coordonneesTondeuse == null) {
			if (other.coordonneesTondeuse != null)
				return false;
			} else if (!coordonneesTondeuse.equals(other.coordonneesTondeuse))
				return false;
		if (orientationTondeuse != other.orientationTondeuse)
			return false;
		return true;
	}
}