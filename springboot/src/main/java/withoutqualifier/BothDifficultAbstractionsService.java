package withoutqualifier;

import org.springframework.stereotype.Repository;

@Repository
public class BothDifficultAbstractionsService {

    private final DifficultAbstraction firstDifficultAbstraction;
    private final DifficultAbstraction secondDifficultAbstraction;

    BothDifficultAbstractionsService(DifficultAbstraction firstDifficultAbstraction,
                                            DifficultAbstraction secondDifficultAbstraction) {
        this.firstDifficultAbstraction = firstDifficultAbstraction;
        this.secondDifficultAbstraction = secondDifficultAbstraction;
    }
}
