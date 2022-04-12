public class Main {

    public static void main(String[] args) {

        App app = new App();
        //instagram kullanıcı adı ve şifre girilecek
        app.loginWith("kullaniciAdi","sifre");

        //hedef instagram kullanici adi girilecek
        app.navigateToProfile("uberkuloz");
        app.clickFirstPost();
        app.likeAllPost();
    }
}
