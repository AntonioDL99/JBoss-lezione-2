package customers;

public class Customer {
    private String nome;
    private String cognome;

    public Customer(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;

    }

    public Customer(){}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
}
