package SopraAJC.NotreProjet.dto;

public class SessionDto {

    private Integer partieId;
    private Integer compteId;
    private boolean aJoueLeTours;
    private boolean tourEnCours;
    private boolean aCommence;

    public SessionDto() {
    }

    public Integer getPartieId() {
        return partieId;
    }

    public void setPartieId(Integer partieId) {
        this.partieId = partieId;
    }

    public Integer getCompteId() {
        return compteId;
    }

    public void setCompteId(Integer compteId) {
        this.compteId = compteId;
    }

    public boolean isaJoueLeTours() {
        return aJoueLeTours;
    }

    public void setaJoueLeTours(boolean aJoueLeTours) {
        this.aJoueLeTours = aJoueLeTours;
    }

    public boolean isTourEnCours() {
        return tourEnCours;
    }

    public void setTourEnCours(boolean tourEnCours) {
        this.tourEnCours = tourEnCours;
    }

    public boolean isaCommence() {
        return aCommence;
    }

    public void setaCommence(boolean aCommence) {
        this.aCommence = aCommence;
    }
}
