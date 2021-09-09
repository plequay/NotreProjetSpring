package SopraAJC.NotreProjet.models;

import com.fasterxml.jackson.annotation.JsonView;

public class JsonViews {

	public static class Common{
		
	}
	
	public static class BatimentWithCout extends Common{
		
	}
	
	public static class SessionBatimentWithSession extends Common {
		
	}
public static class SessionBatimentWithBatiment extends Common {
		
	}
	
}
