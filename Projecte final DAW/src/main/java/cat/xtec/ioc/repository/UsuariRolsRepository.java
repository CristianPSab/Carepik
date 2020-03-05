package cat.xtec.ioc.repository;

import cat.xtec.ioc.domain.UsuarisRols;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UsuariRolsRepository {    
    
    public String Guardar(UsuarisRols ur);
    public String Eliminar(UsuarisRols ur);
    public UsuarisRols Llegir(int codi,int codiusuari);
    public List<UsuarisRols> Llistar(int codi);
    
    public List<UsuarisRols> LlistarRols(int codi);
}