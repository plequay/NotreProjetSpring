package SopraAJC.NotreProjet.models;

import com.fasterxml.jackson.annotation.JsonView;

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

	public static class BatimentWithCout extends Common {}
	
	public static class BatimentWithCoutAndRessourceProduite extends BatimentWithCout {}
	
	public static class BatimentWithCoutAndListeTransformationRessource extends BatimentWithCout {}
	
	public static class SessionBatimentWithSession extends Common {	}

	public static class SessionBatimentWithBatiment extends Common {}
	
	public static class TransformationRessourceWithBatimentAndRessources extends Common {}
	
}
