package sasikala.explore.service.cardapiartifact.authorize;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OfficialsSuccess extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    OfficialsService officialsService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Officials officials = (Officials) authentication.getPrincipal();
        if(officials.getStatus()==1){
            if(officials.getAttempts()>1){
                // reset attempts to 1 since successful login
                officials.setAttempts(1);
                officialsService.updateAttempt(officials);
            }
            super.setDefaultTargetUrl("/dashboard");
        }
        else{
            super.setDefaultTargetUrl("/login");
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
