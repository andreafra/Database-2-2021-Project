package it.polimi.db2.controllers;

import it.polimi.db2.application.entities.Marketing_Question;
import it.polimi.db2.application.entities.User;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/stats_questions")
public class GoToStatsQuestionsPage extends HttpServlet {

	public GoToStatsQuestionsPage() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// If the user is not logged in (not present in session) redirect to the login
		HttpSession session = request.getSession();
		if (session.isNew() || session.getAttribute("user") == null) {
			String loginPath = getServletContext().getContextPath() + "/login";
			response.sendRedirect(loginPath);
			return;
		}

		// Get user
		User user = (User) session.getAttribute("user");

		// Get servlet context
		final WebContext ctx = new WebContext(request, response, getServletContext(), request.getLocale());

		//If the user is banned redirect to the banned page
		if (user.getBanned()) {
			String bannedPath = getServletContext().getContextPath() + "/banned";
			response.sendRedirect(bannedPath);
			return;
		}

		//Retrieve the questionnaire of the day
		try {
//			ArrayList<Stats_Questions> stats_questions = new ArrayList<>();

//			ctx.setVariable("statistical_questions", stats_questions);
		} catch (Exception e) {
			ctx.setVariable("errorMsg", "Couldn't retrieve statistical questions!");
		} finally {
			Thymeleaf.render("stats_questions", ctx);
		}
	}
}