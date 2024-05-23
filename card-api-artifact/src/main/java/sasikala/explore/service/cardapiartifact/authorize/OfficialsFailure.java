package sasikala.explore.service.cardapiartifact.authorize;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OfficialsFailure extends SimpleUrlAuthenticationFailureHandler {
    @Autowired
    OfficialsService officialsService;
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String username = request.getParameter("username");
        Officials officials = (Officials) officialsService.loadUserByUsername(username);
        if(officials!=null){
            if(officials.getStatus()!=0){
                if(officials.getAttempts()<=3){
                    officials.setAttempts(officials.getAttempts()+1);
                    officialsService.updateAttempt(officials);
                    exception = new LockedException("Invalid credentials attempt taken");
                    super.setDefaultFailureUrl("/?error=Invalid credentials attempt taken");
                }
                else{
                    officials.setStatus(0);
                    officialsService.updateStatus(officials);
                    exception = new LockedException("Maximum attempts reached and account blocked");
                    super.setDefaultFailureUrl("/?error=Maximum attempts reached and account blocked");
                }
            }
            else{
                exception = new LockedException("User already blocked");
                super.setDefaultFailureUrl("/error=User already blocked");
            }
        }
        else{
            exception = new LockedException("User doesn't exists");
            super.setDefaultFailureUrl("/login?error=User doesn't exists");
        }
        super.onAuthenticationFailure(request, response, exception);
    }
}
