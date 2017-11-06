package lt.web.service;

import lt.web.models.Fosters;
import lt.web.repository.FostersRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FostersService implements IFosterService{

    @Autowired
    FostersRep fostersRep;

    @Override
    public List<Fosters> getFostersList() {
        return fostersRep.findAll();
    }
}
