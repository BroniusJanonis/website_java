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

    @Override
    public void updateFoster(Fosters fosters) {
        fostersRep.updateFoster(fosters.getName(), fosters.getSurname(), fosters.getPhone(), fosters.getAddress(), fosters.getFosterId(), fosters.getUser().getUserId());
    }

    @Override
    public Fosters saveFoster(Fosters foster) {
        return fostersRep.save(foster);
    }
}
