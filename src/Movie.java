public class Movie {
    static final String CHILDREN = "CHILDREN";
    static final String NEW_RELEASE = "NEW_RELEASE";
    static final String REGULAR = "REGULAR";

    private String MovieName;
    private String Charge;

    public Movie(String MovieName, String Charge) {
        this.MovieName = MovieName;
        this.Charge = Charge;
    }
}
