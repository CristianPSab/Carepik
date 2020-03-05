package cat.xtec.ioc.repository;

import cat.xtec.ioc.domain.Usuaris;
import cat.xtec.ioc.domain.UsuarisRols;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Jordi
 */
public interface HomeRepository {

    public Usuaris Llegir(int codi);
    public String getNomUsuariByCodi(String nom);
//    public String Logout(Usuaris us);

}
