package ewha_hackathon.security.Handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

public class FailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, AuthenticationException exception) throws IOException, jakarta.servlet.ServletException {
        response.sendRedirect(request.getContextPath() + "/login?error=true");
    }

}
