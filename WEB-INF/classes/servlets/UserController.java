package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.*;
import javax.mail.MessagingException;

import data.UserDB;
import data.ResetPswdDB;
import business.TempUser;
import business.ResetPswd;
import utility.EmailHelper;
import business.User;
import utility.MailUtilGmail;

@WebServlet(name = "UserController", urlPatterns = {"/users"})
public class UserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String url = null;
        String action = request.getParameter("action");
        if (action == null) {
            String host = request.getRemoteHost();
            String port = String.valueOf(request.getRemotePort());
            Cookie c1 = new Cookie("host", host);
            c1.setMaxAge(60 * 60 * 12);
            c1.setPath("/");
            response.addCookie(c1);
            Cookie c2 = new Cookie("port", port);
            c2.setMaxAge(60);
            c2.setPath("/");
            response.addCookie(c2);
            url = "/home.jsp";
        } else if (action.equals("login")) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            boolean flag = UserDB.validateUser(email, password);
            if (flag) {
                User user = UserDB.getUserByEmail(email);
                if (user.getType().equals("Participant")) {
                    session.setAttribute("theUser", user);
                    url = "/main.jsp";
                }
                if (user.getType().equals("Admin")) {
                    session.setAttribute("theAdmin", user);
                    url = "/admin.jsp";
                }
            } else {
                String msg = "Invalid Login Information.";
                request.setAttribute("loginErrorMsg", msg);
                url = "/login.jsp";
            }
        } else if (action.equals("create")) {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirm_password");
            String recommender = request.getParameter("recommender");
            if (password.equals(confirmPassword)) {
                java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
                String token = UUID.randomUUID().toString();
                TempUser tempUser = new TempUser();
                tempUser.setUseremail(email);
                tempUser.setUsername(name);
                tempUser.setPassword(confirmPassword);
                tempUser.setIssueDate(date);
                tempUser.setToken(token);
                tempUser.setRecommender(recommender);
                UserDB.addTempUser(tempUser);
                String subject = EmailHelper.getNewUserSubject();
                String body;
                if (recommender == null || recommender == "") {
                    body = EmailHelper.getNewUserBody(email, token);
                } else {
                    body = EmailHelper.getNewUserBodyWithRecommender(email, token, recommender);
                }
                try {
                    MailUtilGmail.sendMail(email, subject, body, false);
                    url = "/login.jsp";
                } catch (MessagingException ex) {
                    String msg = "Failed to send email.";
                    request.setAttribute("signupErrorMsg", msg);
                    url = "/signup.jsp";
                }
            } else {
                String msg = "Invalid Login Information.";
                request.setAttribute("signupErrorMsg", msg);
                url = "/signup.jsp";
            }
        } else if (action.equals("activate")) {
            String token = request.getParameter("token");
            String recommender = request.getParameter("recommender");
            boolean flag = UserDB.validateTempUser(token);
            if (flag) {
                String email = UserDB.activateUser(token);
                User newUser = UserDB.getUserByEmail(email);
                session.setAttribute("theUser", newUser);
                if (recommender != null) {
                    User user = UserDB.getUserByEmail(recommender);
                    int coin = user.getCoins();
                    user.setCoins(coin + 2);
                    UserDB.updateUser(user);
                }
                url = "/main.jsp";
            } else {
                String msg = "Invalid Activation Information.";
                request.setAttribute("signupErrorMsg", msg);
                url = "/signup.jsp";
            }
        } else if (action.equals("forgetPswd")) {
            String email = request.getParameter("email");
            String token = UUID.randomUUID().toString();
            java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
            ResetPswd resetPswd = new ResetPswd();
            resetPswd.setToken(token);
            resetPswd.setUseremail(email);
            resetPswd.setIssueDate(date);
            //***need to be refined
            resetPswd.setExpirationDate(date);
            ResetPswdDB.addResetPwRequest(resetPswd);
            String subject = EmailHelper.getForgetPasswordSubject();
            String body = EmailHelper.getForgetPasswordBody(token);
            try {
                MailUtilGmail.sendMail(email, subject, body, false);
                url = "/login.jsp";
            } catch (MessagingException ex) {
                String msg = "Failed to send email.";
                request.setAttribute("loginErrorMsg", msg);
                url = "/login.jsp";
            }
        } else if (action.equals("resetPswd")) {
            String token = request.getParameter("token");
            ResetPswd resetPswd = ResetPswdDB.getResetPwRequestByToken(token);
            String email = resetPswd.getUseremail();
            request.setAttribute("userEmail", email);
            request.setAttribute("token", token);
            url = "/resetpswd.jsp";
        } else if (action.equals("updatePswd")) {
            String token = request.getParameter("token");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirm_password");
            if (password.equals(confirmPassword)) {
                ResetPswdDB.updatePassword(token, password);
                String msg = "Password successfully reset.";
                request.setAttribute("loginErrorMsg", msg);
                url = "/login.jsp";
            } else {
                ResetPswd resetPswd = ResetPswdDB.getResetPwRequestByToken(token);
                String email = resetPswd.getUseremail();
                String msg = "Passwords don't match.";
                request.setAttribute("userEmail", email);
                request.setAttribute("token", token);
                request.setAttribute("resetPswdErrorMsg", msg);
                url = "/resetpswd.jsp";
            }
        } else if (action.equals("how")) {
            if (session.getAttribute("theUser") != null) {
                url = "/main.jsp";
            } else {
                url = "/how.jsp";
            }
        } else if (action.equals("about")) {
            if (session.getAttribute("theUser") != null) {
                url = "/about.jsp";
            } else {
                url = "/aboutl.jsp";
            }
        } else if (action.equals("home")) {
            if (session.getAttribute("theUser") != null) {
                url = "/main.jsp";
            } else {
                url = "/home.jsp";
            }
        } else if (action.equals("main")) {
            if (session.getAttribute("theUser") != null) {
                url = "/main.jsp";
            } else {
                url = "/login.jsp";
            }
        } else if (action.equals("recommend")) {
            String recommendFrom = request.getParameter("recommendFrom");
            String recommendTo = request.getParameter("recommendTo");
            String message = request.getParameter("message");
            User sessionUser = (User) session.getAttribute("theUser");
            String errorMsg;

            if (sessionUser.getUseremail().equals(recommendFrom)) {
                String subject = EmailHelper.getRecommendationSubject();
                String body = EmailHelper.getRecommendationBody(sessionUser.getUsername(), sessionUser.getUseremail(), message);
                try {
                    MailUtilGmail.sendMail(recommendTo, subject, body, false);
                    url = "/confirmr.jsp";
                } catch (MessagingException ex) {
                    errorMsg = "Unable to send email";
                    request.setAttribute("errorMsg", errorMsg);
                    url = "/recommend.jsp";
                }
            } else {
                errorMsg = "Please enter the email you registered with.";
                request.setAttribute("errorMsg", errorMsg);
                url = "/recommend.jsp";
            }

        } else if (action.equals("beginSignup")) {
            String recommender = request.getParameter("recommender");
            request.setAttribute("recommender", recommender);
            url = "/signup.jsp";
        } else if (action.equals("contact")) {
            String userEmail = request.getParameter("email");
            User sessionUser = (User) session.getAttribute("theUser");
            String errorMsg;
            //if (sessionUser.getUseremail().equals(userEmail)) {
            if (userEmail != null) {
                String subject = EmailHelper.getContactSubject();
                String body = EmailHelper.getContactBody(sessionUser.getUsername());
                try {
                    MailUtilGmail.sendMail(userEmail, subject, body, false);
                    url = "/confirmc.jsp";
                } catch (MessagingException ex) {
                    errorMsg = "Unable to send email";
                    request.setAttribute("errorMsg", errorMsg);
                    url = "/contact.jsp";
                }
            } else {
                errorMsg = "Please enter email...";
                request.setAttribute("errorMsg", errorMsg);
                url = "/contact.jsp";
            }

        } else if (action.equals("logout")) {
            if (session.getAttribute("theUser") != null || session.getAttribute("theAdmin") != null) {
                session.invalidate();
                url = "/home.jsp";
            } else {
                url = "/home.jsp";
            }
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }
}
