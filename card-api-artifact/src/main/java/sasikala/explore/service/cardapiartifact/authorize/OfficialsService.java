package sasikala.explore.service.cardapiartifact.authorize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class OfficialsService implements UserDetailsService {
    @Autowired
    JdbcTemplate jdbcTemplate;



    public Officials signUP(Officials officials){
        jdbcTemplate.update("insert into mybank_officials(fullName,username,password,contact,role) values(?,?,?,?,?)",new Object[]{
                officials.getFullName(),officials.getUsername(),officials.getPassword(),
                officials.getContact(),officials.getRole()
        });
        return officials;
    }


    public Officials getByUsername(String username){
        Officials officials = jdbcTemplate.queryForObject("select * from mybank_officials where username=?",
                new Object[]{username},new BeanPropertyRowMapper<>(Officials.class));
        return officials;
    }

    public void updateAttempt(Officials officials){
        jdbcTemplate.update("update mybank_officials set attempts=? where username=?",
                new Object[]{officials.getAttempts(),officials.getUsername()});
    }

    public void updateStatus(Officials officials){
        jdbcTemplate.update("update mybank_officials set status=? where username=?",
                new Object[]{officials.getStatus(),officials.getUsername()});
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Officials officials = getByUsername(username);
        if(officials==null)
            throw new UsernameNotFoundException(username);
        return officials;
    }
}
