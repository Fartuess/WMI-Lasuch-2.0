package pl.edu.amu.lasuch.solr;

public class Strings {
	public static final String URL_BASE = "http://localhost:8983/solr/collection1";
	public static final String URL_SELECT2 = URL_BASE + "/select?q=%s:%s&wt=json&indent=true";
	public static final String URL_SELECT4 = URL_BASE + "/select?q=%s:%s%%20%s:%s&wt=json&indent=true";
	public static final String URL_SELECT_ALL = URL_BASE + "/select?q=*:*&wt=json&indent=true";

	public static final String URL_UPDATE = "http://localhost:8983/solr/update?commit=true";
	
	public static final String ADD_DOCUMENT = "{ \"add\": { \"doc\" : %s } }";
	public static final String PRODUCT_NOT_FOUND = "Nie znaleziono produktu";
	
	public static void main(String[] args) {
		//THIS MAIN IS FOR LULZ, TFU! FOR TESTS! I MEANT TESTS.
		
		http://localhost:8983/solr/collection1/select?q=*:*&wt=json&indent=true
		System.out.print(String.format(URL_SELECT2, "first1", "first2"));
	}
}
