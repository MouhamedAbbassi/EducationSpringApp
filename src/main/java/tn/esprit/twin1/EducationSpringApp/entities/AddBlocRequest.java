package tn.esprit.twin1.EducationSpringApp.entities;


public class AddBlocRequest {
    private String nomFoyer;
    String nomBloc;
    Long capaciteBloc;

    public AddBlocRequest() {
    }

    public AddBlocRequest(String  nomFoyer, String nomBloc, Long capaciteBloc) {
        this.nomFoyer = nomFoyer;
        this.nomBloc = nomBloc;
        this.capaciteBloc = capaciteBloc;
    }

    public String getNomFoyer() {
        return nomFoyer;
    }

    public void setNomFoyer(String nomFoyer) {
        this.nomFoyer = nomFoyer;
    }

    public String getNomBloc() {
        return nomBloc;
    }

    public void setNomBloc(String nomBloc) {
        this.nomBloc = nomBloc;
    }

    public Long getCapaciteBloc() {
        return capaciteBloc;
    }

    public void setCapaciteBloc(Long capaciteBloc) {
        this.capaciteBloc = capaciteBloc;
    }
}