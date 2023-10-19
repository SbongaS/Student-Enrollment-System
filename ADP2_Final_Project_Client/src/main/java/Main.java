
/**
 *  @authors
 *  1.Asimbonge Mbende(221090754)
 *  2.Thandolwethu Zamasiba Khoza(221797289)
 *  3.Sbonga Shweni(219143188)
 */
public class Main {

    public static void main(String[] args) {
        Client client = new Client();
        LoginApp login = new LoginApp(client);
        login.setGUI();

    }
}
