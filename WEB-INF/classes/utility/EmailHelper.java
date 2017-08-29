package utility;

public class EmailHelper {

    public static String getNewUserSubject() {
        String subject = "Researcher Exchange Registration Activation";
        return subject;
    }

    public static String getNewUserBody(String email, String token) {
        String body = "Welcome to <Researchers Exchange>! Thankyou for registering.\n\n"
                + "This is an activation email. Click on the link below to activate your account.\n\n"
                + "http://abhishekasg4-abhisheknbad.rhcloud.com/Abhishek_Assignment4/users?" + "action=activate" + "&token=" + token;
        return body;
    }

    public static String getNewUserBodyWithRecommender(String email, String token, String recommender) {
        String body = "Welcome to <Researchers Exchange>! Thankyou for registering.\n\n"
                + "This is an activation email. Click on the link below to activate your account.\n\n"
                + "http://abhishekasg4-abhisheknbad.rhcloud.com/Abhishek_Assignment4/users?" + "action=activate" + "&token=" + token + "&recommender=" + recommender;
        return body;
    }

    public static String getForgetPasswordSubject() {
        String subject = "Researcher Exchange Reset Your Password";
        return subject;
    }

    public static String getForgetPasswordBody(String token) {
        String body = "Dear <Researchers Exchange> participant, please click on the link below to reset your password.\n\n"
                + "http://abhishekasg4-abhisheknbad.rhcloud.com/Abhishek_Assignment4/users?" + "action=resetPswd" + "&token=" + token;
        return body;
    }

    public static String getRecommendationSubject() {
        String subject = "Recommendation for joining <Researcher Exchange>";
        return subject;
    }

    public static String getRecommendationBody(String userName, String userEmail, String message) {
        String body = "Hello,\n\n"
                + "Your friend " + userName + "<" + userEmail + ">" + " has recommended you to join and sent this message.\n\n"
                + message + " \n\n"
                + "If you are interested. Please click on the link below to complete the signup process and join in the research community. Thank you.\n"
                + "http://abhishekasg4-abhisheknbad.rhcloud.com/Abhishek_Assignment4/users?" + "action=beginSignup" + "&recommender=" + userEmail;
        return body;
    }
//localhost:8084
    public static String getContactSubject() {
        String subject = "Contact confirmation";
        return subject;
    }

    public static String getContactBody(String userName) {
        String body = "Hello " + userName + ",\n"
                + "Thank you for contacting us. Our support will soon get in touch with you.";
        return body;
    }
}
