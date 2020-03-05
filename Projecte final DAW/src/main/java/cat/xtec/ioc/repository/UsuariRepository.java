package cat.xtec.ioc.repository;

import cat.xtec.ioc.domain.Usuaris;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Jordi
 */
public interface UsuariRepository {    
    
    public String Login(Usuaris us);
    public String Registre(Usuaris us);
    public int getCodiByToken(String token);
    public Usuaris GetUsuari(int codi);
}
