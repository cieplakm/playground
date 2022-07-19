package withoutqualifier;

import org.springframework.stereotype.Service;

@Service
class FirstAbstractionRequiredService {

    private final DifficultAbstraction difficultAbstraction;

    FirstAbstractionRequiredService(DifficultAbstraction firstDifficultAbstraction) {
        this.difficultAbstraction = firstDifficultAbstraction;
    }
}
