package cat.xtec.ioc.service;

import cat.xtec.ioc.domain.UsuarisRols;
import java.util.List;

public interface UsuariRolsService {
    public String Guardar(UsuarisRols ur);
    public String Eliminar(UsuarisRols ur);
    public UsuarisRols Llegir(int codi,int codiusuari);
    public List<UsuarisRols> Llistar(int codi); 
    public List<UsuarisRols> LlistarRols(int codi);
}
