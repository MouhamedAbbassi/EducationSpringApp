package tn.esprit.twin1.EducationSpringApp.entities;


public class AddBlocRequest {
    private long idFoyer;
    String nomBloc;
    Long capaciteBloc;

    public AddBlocRequest() {
    }

    public AddBlocRequest(long idFoyer, String nomBloc, Long capaciteBloc) {
        this.idFoyer = idFoyer;
        this.nomBloc = nomBloc;
        this.capaciteBloc = capaciteBloc;
    }

    public long getIdFoyer() {
        return idFoyer;
    }

    public void setIdFoyer(long idFoyer) {
        this.idFoyer = idFoyer;
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