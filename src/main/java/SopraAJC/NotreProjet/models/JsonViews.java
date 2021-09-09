package SopraAJC.NotreProjet.models;

public class JsonViews {

	public static class Common {}

	public static class SessionWithSessionBatiment extends Common {}

	public static class SessionWithSessionRessource extends Common {}

	public static class SessionWithSessionBatimentAndRessource extends Common {}

	public static class SessionWithPartie extends Common {}

	public static class SessionWithCompte extends Common {}

	public static class SessionWithPartieAndCompte extends Common {}

	public static class CompteWithSession extends Common {}

	public static class PartieWithSession extends Common {}

}
