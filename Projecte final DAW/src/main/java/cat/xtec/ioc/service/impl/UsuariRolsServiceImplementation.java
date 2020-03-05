package cat.xtec.ioc.service.impl;

import cat.xtec.ioc.domain.UsuarisRols;
import cat.xtec.ioc.repository.UsuariRolsRepository;
import cat.xtec.ioc.service.UsuariRolsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuariRolsServiceImplementation implements UsuariRolsService {

    @Autowired
    private UsuariRolsRepository usuariRolsRepository;
    
    @Override
    public String Guardar(UsuarisRols ur) {
        return usuariRolsRepository.Guardar(ur);
    }

    @Override
    public String Eliminar(UsuarisRols ur) {
        return usuariRolsRepository.Eliminar(ur);
    }

    @Override
    public UsuarisRols Llegir(int codi,int codiusuari) {
        return usuariRolsRepository.Llegir(codi,codiusuari);
    }

    @Override
    public List<UsuarisRols> Llistar(int codi) {
        return usuariRolsRepository.Llistar(codi);
    }
    
    @Override
    public List<UsuarisRols> LlistarRols(int codi) {
        return usuariRolsRepository.LlistarRols(codi);
    }
    
}
