package SopraAJC.NotreProjet.dto;

public class SessionDto {

    private Long partieId;
    private Long compteId;
    private boolean aJoueLeTours;
    private boolean tourEnCours;
    private boolean aCommence;

    public SessionDto() {
    }

    public Long getPartieId() {
        return partieId;
    }

    public void setPartieId(Long partieId) {
        this.partieId = partieId;
    }

    public Long getCompteId() {
        return compteId;
    }

    public void setCompteId(Long compteId) {
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
